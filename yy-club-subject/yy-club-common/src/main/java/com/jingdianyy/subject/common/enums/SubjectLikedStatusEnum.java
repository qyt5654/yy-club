package com.jingdianyy.subject.common.enums;

import lombok.Data;
import lombok.Getter;

/**
 * 题目点赞枚举
 */
@Getter
public enum SubjectLikedStatusEnum {

    LIKED(1, "点赞"),
    UNLIKED(0, "取消点赞");

    public int code;

    public String desc;

    SubjectLikedStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CategoryTypeEnum getByCode(int code) {
        for(CategoryTypeEnum categoryTypeEnum : CategoryTypeEnum.values()) {
            if(categoryTypeEnum.code == code) {
                return categoryTypeEnum;
            }
        }
        return null;
    }

}
