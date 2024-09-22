package com.jingdianyy.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签dto
 */
@Data
public class SubjectLabelBo implements Serializable {
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

