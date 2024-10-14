package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectCategoryDTO;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T17:33:50+0800",
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
        subjectCategoryBo.setCount( subjectCategoryDTO.getCount() );

        return subjectCategoryBo;
    }

    @Override
    public List<SubjectCategoryDTO> converterBoToCategoryDTOList(List<SubjectCategoryBo> subjectCategoryBo) {
        if ( subjectCategoryBo == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( subjectCategoryBo.size() );
        for ( SubjectCategoryBo subjectCategoryBo1 : subjectCategoryBo ) {
            list.add( converterBoToDTO( subjectCategoryBo1 ) );
        }

        return list;
    }

    @Override
    public SubjectCategoryDTO converterBoToDTO(SubjectCategoryBo bo) {
        if ( bo == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( bo.getId() );
        subjectCategoryDTO.setCategoryName( bo.getCategoryName() );
        subjectCategoryDTO.setCategoryType( bo.getCategoryType() );
        subjectCategoryDTO.setImageUrl( bo.getImageUrl() );
        subjectCategoryDTO.setParentId( bo.getParentId() );
        subjectCategoryDTO.setCount( bo.getCount() );

        return subjectCategoryDTO;
    }
}
