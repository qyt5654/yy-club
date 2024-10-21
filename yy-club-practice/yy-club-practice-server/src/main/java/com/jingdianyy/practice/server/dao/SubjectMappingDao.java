package com.jingdianyy.practice.server.dao;

import com.jingdianyy.practice.server.entity.po.LabelCountPO;
import com.jingdianyy.practice.server.entity.po.SubjectMappingPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-21 19:55:38
 */
public interface SubjectMappingDao {

    List<LabelCountPO> getLabelSubjectCount(@Param("categoryId") Long categoryId,
                                            @Param("subjectTypeList") List<Integer> subjectTypeList);

    List<SubjectMappingPO> getLabelIdsBySubjectId(Long subjectId);
}

