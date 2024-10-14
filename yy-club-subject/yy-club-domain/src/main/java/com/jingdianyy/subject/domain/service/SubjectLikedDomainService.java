package com.jingdianyy.subject.domain.service;


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
    Boolean add(SubjectLikedBO subjectLikedBO);

    /**
     * 更新 题目点赞表 信息
     */
    Boolean update(SubjectLikedBO subjectLikedBO);

    /**
     * 删除 题目点赞表 信息
     */
    Boolean delete(SubjectLikedBO subjectLikedBO);

}
