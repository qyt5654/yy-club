package com.jingdianyy.subject.common.enums;

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

    public static ResultCodeEnum getByCode(int code) {
        for(ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if(resultCodeEnum.code == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }

}
