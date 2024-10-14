package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectCategoryDTO;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBo converterBoToCategory(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> converterBoToCategoryDTOList(List<SubjectCategoryBo> subjectCategoryBo);

    SubjectCategoryDTO converterBoToDTO(SubjectCategoryBo bo);
}
