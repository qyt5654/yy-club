package com.jingdianyy.subject.domain.entity;

import com.jingdianyy.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息DTO
 */
@Data
public class SubjectOptionBo implements Serializable {

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBo> optionList;

}

