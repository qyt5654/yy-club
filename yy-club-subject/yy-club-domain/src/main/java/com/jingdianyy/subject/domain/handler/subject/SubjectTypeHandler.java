package com.jingdianyy.subject.domain.handler.subject;

import com.jingdianyy.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.entity.SubjectOptionBo;

public interface SubjectTypeHandler {

    /**
     * 枚举身份的识别
     * @return
     */
    SubjectInfoTypeEnum getSubjectType();

    /**
     * 实际的题目的插入
     * @param subjectInfoBo
     */
    void add(SubjectInfoBo subjectInfoBo);

    /**
     * 题目详情
     * @param subjectId
     * @return
     */
    SubjectOptionBo query(Long subjectId);

}
