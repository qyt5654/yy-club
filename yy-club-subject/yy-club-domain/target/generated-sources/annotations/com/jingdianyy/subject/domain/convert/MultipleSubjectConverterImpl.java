package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectMultiple;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T17:03:42+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class MultipleSubjectConverterImpl implements MultipleSubjectConverter {

    @Override
    public List<SubjectAnswerBo> convertEntityToBoList(List<SubjectMultiple> result) {
        if ( result == null ) {
            return null;
        }

        List<SubjectAnswerBo> list = new ArrayList<SubjectAnswerBo>( result.size() );
        for ( SubjectMultiple subjectMultiple : result ) {
            list.add( subjectMultipleToSubjectAnswerBo( subjectMultiple ) );
        }

        return list;
    }

    @Override
    public SubjectMultiple convertBoToEntity(SubjectAnswerBo option) {
        if ( option == null ) {
            return null;
        }

        SubjectMultiple subjectMultiple = new SubjectMultiple();

        if ( option.getOptionType() != null ) {
            subjectMultiple.setOptionType( option.getOptionType().longValue() );
        }
        subjectMultiple.setOptionContent( option.getOptionContent() );
        subjectMultiple.setIsCorrect( option.getIsCorrect() );

        return subjectMultiple;
    }

    protected SubjectAnswerBo subjectMultipleToSubjectAnswerBo(SubjectMultiple subjectMultiple) {
        if ( subjectMultiple == null ) {
            return null;
        }

        SubjectAnswerBo subjectAnswerBo = new SubjectAnswerBo();

        if ( subjectMultiple.getOptionType() != null ) {
            subjectAnswerBo.setOptionType( subjectMultiple.getOptionType().intValue() );
        }
        subjectAnswerBo.setOptionContent( subjectMultiple.getOptionContent() );
        subjectAnswerBo.setIsCorrect( subjectMultiple.getIsCorrect() );

        return subjectAnswerBo;
    }
}
