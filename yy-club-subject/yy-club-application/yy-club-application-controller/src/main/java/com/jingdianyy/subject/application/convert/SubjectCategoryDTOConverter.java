package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectCategoryDTO;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBo converterBoToCategory(SubjectCategoryDTO subjectCategoryDTO);

}
