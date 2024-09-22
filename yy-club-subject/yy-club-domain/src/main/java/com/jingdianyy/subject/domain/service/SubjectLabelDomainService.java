package com.jingdianyy.subject.domain.service;

import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.domain.entity.SubjectLabelBo;

import java.util.List;

/**
 * 题目标签领域服务
 */
public interface SubjectLabelDomainService {

    /**
     * 新增标签
     * @param subjectLabelBo
     */
    Boolean add(SubjectLabelBo subjectLabelBo);

    /**
     * 修改标签
     * @return
     */
    Boolean update(SubjectLabelBo subjectLabelBo);

    /**
     * 删除标签
     * @param subjectLabelBo
     * @return
     */
    Boolean delete(SubjectLabelBo subjectLabelBo);

    /**
     * 查询分类下标签
     * @param subjectLabelBo
     * @return
     */
    List<SubjectLabelBo> queryLabelByCategoryId(SubjectLabelBo subjectLabelBo);
}
