package com.jingdianyy.practice.server.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author makejava
 * @since 2024-09-21 16:45:28
 */
@Data
public class SubjectLabelPO implements Serializable {
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
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer isDeleted;

}

