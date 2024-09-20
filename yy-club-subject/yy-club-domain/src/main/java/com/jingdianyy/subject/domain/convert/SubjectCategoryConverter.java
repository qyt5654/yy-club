package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectCategoryConverter {

    SubjectCategoryConverter INSTANCE = Mappers.getMapper(SubjectCategoryConverter.class);

    SubjectCategory converterBoToCategory(SubjectCategoryBo subjectCategoryBo);

}
