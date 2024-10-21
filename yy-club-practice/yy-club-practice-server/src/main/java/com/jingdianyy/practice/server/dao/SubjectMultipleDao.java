package com.jingdianyy.practice.server.dao;

import com.jingdianyy.practice.server.entity.po.SubjectMultiplePO;

import java.util.List;

public interface SubjectMultipleDao {

    /**
     * 查询题目
     */
    List<SubjectMultiplePO> selectBySubjectId(Long subjectId);

}