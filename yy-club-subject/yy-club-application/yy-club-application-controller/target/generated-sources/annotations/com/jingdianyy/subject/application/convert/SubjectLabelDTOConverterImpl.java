package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectLabelDTO;
import com.jingdianyy.subject.domain.entity.SubjectLabelBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T17:33:50+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class SubjectLabelDTOConverterImpl implements SubjectLabelDTOConverter {

    @Override
    public SubjectLabelBo converterDtoToLabelBo(SubjectLabelDTO subjectLabelDTO) {
        if ( subjectLabelDTO == null ) {
            return null;
        }

        SubjectLabelBo subjectLabelBo = new SubjectLabelBo();

        subjectLabelBo.setId( subjectLabelDTO.getId() );
        subjectLabelBo.setLabelName( subjectLabelDTO.getLabelName() );
        subjectLabelBo.setSortNum( subjectLabelDTO.getSortNum() );
        subjectLabelBo.setCategoryId( subjectLabelDTO.getCategoryId() );

        return subjectLabelBo;
    }

    @Override
    public List<SubjectLabelDTO> converterLabelBoToDtoList(List<SubjectLabelBo> subjectLabelBoList) {
        if ( subjectLabelBoList == null ) {
            return null;
        }

        List<SubjectLabelDTO> list = new ArrayList<SubjectLabelDTO>( subjectLabelBoList.size() );
        for ( SubjectLabelBo subjectLabelBo : subjectLabelBoList ) {
            list.add( subjectLabelBoToSubjectLabelDTO( subjectLabelBo ) );
        }

        return list;
    }

    protected SubjectLabelDTO subjectLabelBoToSubjectLabelDTO(SubjectLabelBo subjectLabelBo) {
        if ( subjectLabelBo == null ) {
            return null;
        }

        SubjectLabelDTO subjectLabelDTO = new SubjectLabelDTO();

        subjectLabelDTO.setId( subjectLabelBo.getId() );
        subjectLabelDTO.setLabelName( subjectLabelBo.getLabelName() );
        subjectLabelDTO.setSortNum( subjectLabelBo.getSortNum() );
        subjectLabelDTO.setCategoryId( subjectLabelBo.getCategoryId() );

        return subjectLabelDTO;
    }
}
