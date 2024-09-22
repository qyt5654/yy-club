package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectInfoDTO;
import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBo converterDTOToBo(SubjectInfoDTO subjectInfoDTO);

    PageResult<SubjectInfoDTO> converterBoToPageResultDTO(PageResult<SubjectInfoBo> pageResult);

}
