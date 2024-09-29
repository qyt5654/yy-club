package com.jingdianyy.auth.domain.service.Impl;

import com.jingdianyy.auth.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.auth.domain.convert.AuthPermissionBoConvert;
import com.jingdianyy.auth.domain.entity.AuthPermissionBo;
import com.jingdianyy.auth.domain.service.AuthPermissionDomainService;
import com.jingdianyy.auth.infra.basic.entity.AuthPermission;
import com.jingdianyy.auth.infra.basic.service.AuthPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

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
}
