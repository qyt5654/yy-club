package com.jingdianyy.auth.domain.service.Impl;

import com.jingdianyy.auth.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.auth.domain.entity.AuthRolePermissionBo;
import com.jingdianyy.auth.domain.service.AuthRolePermissionDomainService;
import com.jingdianyy.auth.infra.basic.entity.AuthRolePermission;
import com.jingdianyy.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBo authRolePermissionBo) {
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();
        Long roleId = authRolePermissionBo.getRoleId();
        authRolePermissionBo.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
            rolePermissionList.add(authRolePermission);
//            authRolePermissionService.insert(authRolePermission);
        });
        int count = authRolePermissionService.insertBatch(rolePermissionList);
        return count == rolePermissionList.size();
    }
}
