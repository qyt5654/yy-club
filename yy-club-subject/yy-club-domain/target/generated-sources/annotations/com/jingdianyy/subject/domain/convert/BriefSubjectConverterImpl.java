package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectBrief;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-20T21:48:55+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class BriefSubjectConverterImpl implements BriefSubjectConverter {

    @Override
    public SubjectBrief convertBoToEntity(SubjectInfoBo subjectInfoBo) {
        if ( subjectInfoBo == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        subjectBrief.setId( subjectInfoBo.getId() );
        subjectBrief.setSubjectAnswer( subjectInfoBo.getSubjectAnswer() );

        return subjectBrief;
    }
}
