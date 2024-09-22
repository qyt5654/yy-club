package com.jingdianyy.subject.infra.basic.service.impl;

import com.jingdianyy.subject.infra.basic.entity.SubjectMapping;
import com.jingdianyy.subject.infra.basic.mapper.SubjectMappingDao;
import com.jingdianyy.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务实现类
 *
 * @author makejava
 * @since 2024-09-21 19:55:38
 */
@Service("subjectMappingService")
public class SubjectMappingServiceImpl implements SubjectMappingService {
    @Resource
    private SubjectMappingDao subjectMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMapping queryById(Long id) {
        return this.subjectMappingDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectMapping subjectMapping) {
        return this.subjectMappingDao.insert(subjectMapping);
    }

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectMapping subjectMapping) {
        return this.subjectMappingDao.update(subjectMapping);
    }

    @Override
    public List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping) {
        return this.subjectMappingDao.queryDistinctLabelId(subjectMapping);
    }

    /**
     * 批量插入
     * @param subjectMappings
     */
    @Override
    public void batchInsert(List<SubjectMapping> subjectMappings) {
        this.subjectMappingDao.insertBatch(subjectMappings);
    }
}
