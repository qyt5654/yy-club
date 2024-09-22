package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectCategoryDTO;
import com.jingdianyy.subject.application.dto.SubjectLabelDTO;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.domain.entity.SubjectLabelBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签dto的转换
 */
@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBo converterDtoToLabelBo(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> converterLabelBoToDtoList(List<SubjectLabelBo> subjectLabelBoList);

}
