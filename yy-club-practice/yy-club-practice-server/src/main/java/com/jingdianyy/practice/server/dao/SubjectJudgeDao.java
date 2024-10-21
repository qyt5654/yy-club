package com.jingdianyy.practice.server.dao;

import com.jingdianyy.practice.server.entity.po.SubjectJudgePO;

public interface SubjectJudgeDao {


    SubjectJudgePO selectBySubjectId(Long repeatSubjectId);


}