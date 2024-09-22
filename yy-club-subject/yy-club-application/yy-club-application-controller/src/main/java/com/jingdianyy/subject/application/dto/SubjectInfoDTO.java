package com.jingdianyy.subject.application.dto;

import com.jingdianyy.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目信息DTO
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 分类id集合
     */
    private List<Integer> categoryIds;
    /**
     * 标签id集合
     */
    private List<Integer> labelIds;
    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 标签id
     */
    private Long labelId;

}

