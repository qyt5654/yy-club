package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectAnswerDTO;
import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-20T21:48:57+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class SubjectAnswerDTOConverterImpl implements SubjectAnswerDTOConverter {

    @Override
    public List<SubjectAnswerBo> converterDTOToBo(List<SubjectAnswerDTO> subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        List<SubjectAnswerBo> list = new ArrayList<SubjectAnswerBo>( subjectAnswerDTO.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO1 : subjectAnswerDTO ) {
            list.add( subjectAnswerDTOToSubjectAnswerBo( subjectAnswerDTO1 ) );
        }

        return list;
    }

    protected SubjectAnswerBo subjectAnswerDTOToSubjectAnswerBo(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBo subjectAnswerBo = new SubjectAnswerBo();

        subjectAnswerBo.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBo.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBo.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBo;
    }
}
