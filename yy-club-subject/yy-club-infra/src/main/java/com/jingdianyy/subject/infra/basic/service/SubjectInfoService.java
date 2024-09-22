package com.jingdianyy.subject.infra.basic.service;

import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-09-22 13:56:00
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询数量
     * @param subjectInfo
     * @param labelId
     * @param categoryId
     * @return
     */
    int countByCondition(SubjectInfo subjectInfo, Long labelId, Long categoryId);

    List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Long labelId, Long categoryId, int start, Integer pageSize);
}
