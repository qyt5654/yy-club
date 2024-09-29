package com.jingdianyy.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限Bo
 *
 * @author makejava
 * @since 2024-09-29 19:36:04
 */
@Data
public class AuthPermissionBo implements Serializable {

    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 权限类型 0菜单 1操作
     */
    private Integer type;
    /**
     * 菜单路由
     */
    private String menuUrl;
    /**
     * 状态 0启用 1禁用
     */
    private Integer status;
    /**
     * 展示状态 0展示 1隐藏
     */
    private Integer show;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限唯一标识
     */
    private String permissionKey;
}

