package com.jingdianyy.auth.domain.service;

import com.jingdianyy.auth.domain.entity.AuthPermissionBo;

import java.util.List;

public interface AuthPermissionDomainService {

    /**
     * 新增权限
     * @param authPermissionBo
     * @return
     */
    Boolean add(AuthPermissionBo authPermissionBo);

    /**
     * 修改权限
     * @param authPermissionBo
     * @return
     */
    Boolean update(AuthPermissionBo authPermissionBo);

    /**
     * 删除权限
     * @param authPermissionBo
     * @return
     */
    Boolean delete(AuthPermissionBo authPermissionBo);

    /**
     * 获取用户权限
     * @param userName
     * @return
     */
    List<String> getPermission(String userName);
}
