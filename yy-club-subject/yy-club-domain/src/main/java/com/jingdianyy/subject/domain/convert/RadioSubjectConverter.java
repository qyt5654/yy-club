package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectCategory;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;
import com.jingdianyy.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RadioSubjectConverter {

    RadioSubjectConverter INSTANCE = Mappers.getMapper(RadioSubjectConverter.class);

    List<SubjectAnswerBo> convertEntityToBoList(List<SubjectRadio> result);

    SubjectRadio convertBoToEntity(SubjectAnswerBo option);
}
