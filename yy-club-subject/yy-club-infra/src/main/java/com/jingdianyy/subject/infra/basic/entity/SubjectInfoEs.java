package com.jingdianyy.subject.infra.basic.entity;

import com.jingdianyy.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SubjectInfoEs extends PageInfo implements Serializable {

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 文档id
     */
    private Long docId;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 题目类型
     */
    private Integer subjectType;

    /**
     * 关键词
     */
    private String keyWord;

    /**
     * 分数
     */
    private BigDecimal score;

}
