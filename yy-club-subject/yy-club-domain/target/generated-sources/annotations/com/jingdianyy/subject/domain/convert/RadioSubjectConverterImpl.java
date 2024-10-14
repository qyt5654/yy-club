package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectRadio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T17:33:48+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class RadioSubjectConverterImpl implements RadioSubjectConverter {

    @Override
    public List<SubjectAnswerBo> convertEntityToBoList(List<SubjectRadio> result) {
        if ( result == null ) {
            return null;
        }

        List<SubjectAnswerBo> list = new ArrayList<SubjectAnswerBo>( result.size() );
        for ( SubjectRadio subjectRadio : result ) {
            list.add( subjectRadioToSubjectAnswerBo( subjectRadio ) );
        }

        return list;
    }

    @Override
    public SubjectRadio convertBoToEntity(SubjectAnswerBo option) {
        if ( option == null ) {
            return null;
        }

        SubjectRadio subjectRadio = new SubjectRadio();

        subjectRadio.setOptionType( option.getOptionType() );
        subjectRadio.setOptionContent( option.getOptionContent() );
        subjectRadio.setIsCorrect( option.getIsCorrect() );

        return subjectRadio;
    }

    protected SubjectAnswerBo subjectRadioToSubjectAnswerBo(SubjectRadio subjectRadio) {
        if ( subjectRadio == null ) {
            return null;
        }

        SubjectAnswerBo subjectAnswerBo = new SubjectAnswerBo();

        subjectAnswerBo.setOptionType( subjectRadio.getOptionType() );
        subjectAnswerBo.setOptionContent( subjectRadio.getOptionContent() );
        subjectAnswerBo.setIsCorrect( subjectRadio.getIsCorrect() );

        return subjectAnswerBo;
    }
}
