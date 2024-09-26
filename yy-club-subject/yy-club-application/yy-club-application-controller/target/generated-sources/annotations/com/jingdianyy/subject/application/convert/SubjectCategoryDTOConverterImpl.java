package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectCategoryDTO;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T17:03:43+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class SubjectCategoryDTOConverterImpl implements SubjectCategoryDTOConverter {

    @Override
    public SubjectCategoryBo converterBoToCategory(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBo subjectCategoryBo = new SubjectCategoryBo();

        subjectCategoryBo.setId( subjectCategoryDTO.getId() );
        subjectCategoryBo.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBo.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBo.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBo.setParentId( subjectCategoryDTO.getParentId() );

        return subjectCategoryBo;
    }

    @Override
    public List<SubjectCategoryDTO> converterBoToCategoryDTOList(List<SubjectCategoryBo> subjectCategoryBo) {
        if ( subjectCategoryBo == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( subjectCategoryBo.size() );
        for ( SubjectCategoryBo subjectCategoryBo1 : subjectCategoryBo ) {
            list.add( subjectCategoryBoToSubjectCategoryDTO( subjectCategoryBo1 ) );
        }

        return list;
    }

    protected SubjectCategoryDTO subjectCategoryBoToSubjectCategoryDTO(SubjectCategoryBo subjectCategoryBo) {
        if ( subjectCategoryBo == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( subjectCategoryBo.getId() );
        subjectCategoryDTO.setCategoryName( subjectCategoryBo.getCategoryName() );
        subjectCategoryDTO.setCategoryType( subjectCategoryBo.getCategoryType() );
        subjectCategoryDTO.setImageUrl( subjectCategoryBo.getImageUrl() );
        subjectCategoryDTO.setParentId( subjectCategoryBo.getParentId() );

        return subjectCategoryDTO;
    }
}
