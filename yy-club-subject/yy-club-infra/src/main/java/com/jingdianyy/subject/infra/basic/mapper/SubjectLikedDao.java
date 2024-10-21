package com.jingdianyy.subject.infra.basic.mapper;

import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;
import com.jingdianyy.subject.infra.basic.entity.SubjectLiked;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题目点赞表 表数据库访问层
 *
 * @author jingdianyy
 * @since 2024-10-14 19:56:47
 */
@Repository
public interface SubjectLikedDao extends BaseMapper<SubjectLiked> {

    void batchInsert(@Param("entities") List<SubjectLiked> entities);

    int countByCondition(@Param("subjectLiked") SubjectLiked subjectLiked);

    List<SubjectLiked> queryPage(@Param("subjectLiked") SubjectLiked subjectLiked,
                                @Param("start") int start,
                                @Param("pageSize") Integer pageSize);
}

