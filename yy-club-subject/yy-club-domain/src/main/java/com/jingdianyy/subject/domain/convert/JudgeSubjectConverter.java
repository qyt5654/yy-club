package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectJudge;
import com.jingdianyy.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JudgeSubjectConverter {

    JudgeSubjectConverter INSTANCE = Mappers.getMapper(JudgeSubjectConverter.class);

    SubjectJudge convertBoToJudge(SubjectAnswerBo subjectAnswerBo);

}
