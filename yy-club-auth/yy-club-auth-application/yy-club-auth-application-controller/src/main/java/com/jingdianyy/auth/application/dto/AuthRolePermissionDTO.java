package com.jingdianyy.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限关联表DTO
 *
 * @author makejava
 * @since 2024-09-29 21:03:51
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
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

