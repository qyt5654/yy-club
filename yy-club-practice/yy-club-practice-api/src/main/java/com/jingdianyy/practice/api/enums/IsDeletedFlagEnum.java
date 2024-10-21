package com.jingdianyy.practice.api.enums;

import lombok.Getter;

/**
 * 删除状态枚举
 */
@Getter
public enum IsDeletedFlagEnum {

    DELETED(1, "已删除"),
    UN_DELETE(0, "未删除");

    public int code;

    public String desc;

    IsDeletedFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IsDeletedFlagEnum getByCode(int code) {
        for(IsDeletedFlagEnum isDeletedFlagEnum : IsDeletedFlagEnum.values()) {
            if(isDeletedFlagEnum.code == code) {
                return isDeletedFlagEnum;
            }
        }
        return null;
    }

}
