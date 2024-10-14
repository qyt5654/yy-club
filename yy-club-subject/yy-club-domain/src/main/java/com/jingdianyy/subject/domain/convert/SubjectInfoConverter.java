package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.entity.SubjectOptionBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBo subjectInfoBo);

    List<SubjectInfoBo> convertListInfoToBo(List<SubjectInfo> subjectInfoList);

    SubjectInfoBo convertOptionToBo(SubjectOptionBo optionBo);

    SubjectInfoBo convertOptionAndInfoToBo(SubjectOptionBo optionBo, SubjectInfo subjectInfo);
}
