package com.jingdianyy.subject.domain.service;

import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectCategory;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBo subjectCategorybo);

    /**
     * 查询岗位大类
     */
    List<SubjectCategoryBo> queryCategory(SubjectCategoryBo subjectCategoryBo);

    /**
     * 更新分类
     */
    Boolean update(SubjectCategoryBo subjectCategoryBo);

    /**
     * 删除分类
     */
    Boolean delete(SubjectCategoryBo subjectCategoryBo);

    /**
     * 查询分类及标签（一次性）
     * @param subjectCategoryBo
     * @return
     */
    List<SubjectCategoryBo> queryCategoryAndLabel(SubjectCategoryBo subjectCategoryBo) throws ExecutionException, InterruptedException;
}
