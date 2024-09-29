package com.jingdianyy.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关联表领域层Bo
 *
 * @author makejava
 * @since 2024-09-29 21:03:51
 */
@Data
public class AuthRolePermissionBo implements Serializable {
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;
    /**
     * 权限集合
     */
    private List<Long> permissionIdList;
}

