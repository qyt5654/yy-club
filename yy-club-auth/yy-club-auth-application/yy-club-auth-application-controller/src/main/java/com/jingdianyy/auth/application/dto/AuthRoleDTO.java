package com.jingdianyy.auth.application.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色dto
 *
 * @author makejava
 * @since 2024-09-28 20:35:40
 */
@Data
public class AuthRoleDTO implements Serializable {

    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色唯一标识
     */
    private String roleKey;
}

