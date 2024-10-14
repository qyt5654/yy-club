package com.jingdianyy.subject.infra.basic.service;

import com.jingdianyy.subject.infra.basic.entity.SubjectLiked;

/**
 * 题目点赞表 表服务接口
 *
 * @author jingdianyy
 * @since 2024-10-14 19:56:47
 */
public interface SubjectLikedService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLiked queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    int insert(SubjectLiked subjectLiked);

    /**
     * 修改数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    int update(SubjectLiked subjectLiked);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询角色
     */
    SubjectLiked queryByCondition(SubjectLiked subjectLiked);

}
