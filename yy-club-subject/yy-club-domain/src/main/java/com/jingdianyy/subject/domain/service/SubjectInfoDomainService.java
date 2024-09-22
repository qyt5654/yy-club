package com.jingdianyy.subject.domain.service;

import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.entity.SubjectLabelBo;

import java.util.List;

/**
 * 题目领域服务
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBo subjectInfoBo);

    /**
     * 分页查询题目列表
     * @param subjectInfoBo
     * @return
     */
    PageResult<SubjectInfoBo> getSubjectPage(SubjectInfoBo subjectInfoBo);
}
