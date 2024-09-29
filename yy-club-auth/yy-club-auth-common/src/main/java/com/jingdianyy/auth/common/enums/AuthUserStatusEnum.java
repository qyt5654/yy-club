package com.jingdianyy.auth.common.enums;

import lombok.Getter;

/**
 * 用户状态枚举
 */
@Getter
public enum AuthUserStatusEnum {

    OPEN(1, "启用"),
    CLOSE(0, "禁用");

    public int code;

    public String desc;

    AuthUserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthUserStatusEnum getByCode(int code) {
        for(AuthUserStatusEnum isDeletedFlagEnum : AuthUserStatusEnum.values()) {
            if(isDeletedFlagEnum.code == code) {
                return isDeletedFlagEnum;
            }
        }
        return null;
    }

}
