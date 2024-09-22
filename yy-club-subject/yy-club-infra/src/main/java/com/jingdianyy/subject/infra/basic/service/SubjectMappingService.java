package com.jingdianyy.subject.infra.basic.service;

import com.jingdianyy.subject.infra.basic.entity.SubjectMapping;

import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author makejava
 * @since 2024-09-21 19:55:38
 */
public interface SubjectMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMapping queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    int insert(SubjectMapping subjectMapping);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    int update(SubjectMapping subjectMapping);

    /**
     * 查询标签id
     * @param subjectMapping
     * @return
     */
    List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping);

    /**
     * 批量插入
     * @param subjectMappings
     */
    void batchInsert(List<SubjectMapping> subjectMappings);
}
