package com.jingdianyy.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签dto
 */
@Data
public class SubjectLabelDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 分类id
     */
    private Long categoryId;
}

