package com.jingdianyy.subject.domain.service;


import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.domain.entity.SubjectLikedBO;

/**
 * 题目点赞表 领域service
 *
 * @author jingdianyy
 * @since 2024-10-14 19:56:47
 */
public interface SubjectLikedDomainService {

    /**
     * 添加 题目点赞表 信息
     */
    void add(SubjectLikedBO subjectLikedBO);

    /**
     * 获取当前是否被点赞过
     */
    Boolean isLiked(String subjectId, String userId);

    /**
     * 获取当前题目的点赞数量
     */
    Integer getLikedCount(String subjectId);

    /**
     * 更新 题目点赞表 信息
     */
    Boolean update(SubjectLikedBO subjectLikedBO);

    /**
     * 删除 题目点赞表 信息
     */
    Boolean delete(SubjectLikedBO subjectLikedBO);

    /**
     * 同步点赞数据
     */
    void syncLiked();

    /**
     * 查询我的点赞列表
     */
    PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO);

    void syncLikedByMsg(SubjectLikedBO subjectLikedBO);
}
