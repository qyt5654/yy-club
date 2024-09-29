package com.jingdianyy.auth.domain.service.Impl;

import com.jingdianyy.auth.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.auth.domain.convert.AuthRoleBoConvert;
import com.jingdianyy.auth.domain.entity.AuthRoleBo;
import com.jingdianyy.auth.domain.service.AuthRoleDomainService;
import com.jingdianyy.auth.infra.basic.entity.AuthRole;
import com.jingdianyy.auth.infra.basic.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色领域service
 */
@Service
@Slf4j
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBo authRoleBo) {
        AuthRole authRole = AuthRoleBoConvert.INSTANCE.convertBoToEntity(authRoleBo);
        authRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthRoleBo authRoleBo) {
        AuthRole authRole = AuthRoleBoConvert.INSTANCE.convertBoToEntity(authRoleBo);
        int count = authRoleService.update(authRole);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthRoleBo authRoleBo) {
        AuthRole authRole = AuthRoleBoConvert.INSTANCE.convertBoToEntity(authRoleBo);
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = authRoleService.update(authRole);
        return count > 0;
    }
}
