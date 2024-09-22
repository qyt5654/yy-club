package com.jingdianyy.subject.application.dto;

import lombok.Data;

/**
 * 题目答案DTO
 */
@Data
public class SubjectAnswerDTO {
    /**
     * 答案类型
     */
    private Integer optionType;
    /**
     * 答案内容
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;
}
