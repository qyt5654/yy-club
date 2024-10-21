package com.jingdianyy.practice.api.enums;

import lombok.Getter;

/**
 * 分类类型枚举
 */
@Getter
public enum SubjectInfoTypeEnum {

    RADIO(1, "单选"),
    MULTIPLE(2, "多选"),
    JUDGE(3, "判断"),
    BRIEF(4, "简答");

    public int code;

    public String desc;

    SubjectInfoTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypeEnum getByCode(int code) {
        for(SubjectInfoTypeEnum subjectInfoTypeEnum : SubjectInfoTypeEnum.values()) {
            if(subjectInfoTypeEnum.code == code) {
                return subjectInfoTypeEnum;
            }
        }
        return null;
    }

}
