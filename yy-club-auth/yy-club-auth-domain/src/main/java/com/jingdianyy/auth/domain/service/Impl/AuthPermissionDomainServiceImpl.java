package com.jingdianyy.auth.domain.service.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jingdianyy.auth.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.auth.domain.convert.AuthPermissionBoConvert;
import com.jingdianyy.auth.domain.entity.AuthPermissionBo;
import com.jingdianyy.auth.domain.redis.RedisUtil;
import com.jingdianyy.auth.domain.service.AuthPermissionDomainService;
import com.jingdianyy.auth.infra.basic.entity.AuthPermission;
import com.jingdianyy.auth.infra.basic.service.AuthPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    @Override
    public Boolean add(AuthPermissionBo authPermissionBo) {
        AuthPermission authPermission = AuthPermissionBoConvert.INSTANCE.convertBoToEntity(authPermissionBo);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBo authPermissionBo) {
        AuthPermission authPermission = AuthPermissionBoConvert.INSTANCE.convertBoToEntity(authPermissionBo);
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBo authPermissionBo) {
        AuthPermission authPermission = AuthPermissionBoConvert.INSTANCE.convertBoToEntity(authPermissionBo);
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public List<String> getPermission(String userName) {
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, userName);
        String permissionValue = redisUtil.get(permissionKey);
        if(StringUtils.isBlank(permissionValue)) {
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue, new TypeToken<List<AuthPermission>>() {
        }.getType());
        return permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
    }
}
