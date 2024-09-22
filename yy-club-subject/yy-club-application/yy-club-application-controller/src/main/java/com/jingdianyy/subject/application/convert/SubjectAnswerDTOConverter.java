package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectAnswerDTO;
import com.jingdianyy.subject.application.dto.SubjectInfoDTO;
import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    List<SubjectAnswerBo> converterDTOToBo(List<SubjectAnswerDTO> subjectAnswerDTO);

}
