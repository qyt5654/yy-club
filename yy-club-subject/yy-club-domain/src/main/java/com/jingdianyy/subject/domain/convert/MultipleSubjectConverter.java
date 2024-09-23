package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectJudge;
import com.jingdianyy.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MultipleSubjectConverter {

    MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);

    List<SubjectAnswerBo> convertEntityToBoList(List<SubjectMultiple> result);

    SubjectMultiple convertBoToEntity(SubjectAnswerBo option);
}
