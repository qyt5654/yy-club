package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectLikedBO;
import com.jingdianyy.subject.infra.basic.entity.SubjectLiked;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 题目点赞表 bo转换器
 *
 * @author jingdianyy
 * @since 2024-10-14 19:56:47
 */
@Mapper
public interface SubjectLikedBOConverter {

    SubjectLikedBOConverter INSTANCE = Mappers.getMapper(SubjectLikedBOConverter.class);

    SubjectLiked convertBOToEntity(SubjectLikedBO subjectLikedBO);

    List<SubjectLikedBO> convertListInfoToBo(List<SubjectLiked> subjectLikedList);
}
