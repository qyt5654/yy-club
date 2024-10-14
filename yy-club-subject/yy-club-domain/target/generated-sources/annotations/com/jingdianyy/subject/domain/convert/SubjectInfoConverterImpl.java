package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.entity.SubjectOptionBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T17:33:48+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class SubjectInfoConverterImpl implements SubjectInfoConverter {

    @Override
    public SubjectInfo convertBoToInfo(SubjectInfoBo subjectInfoBo) {
        if ( subjectInfoBo == null ) {
            return null;
        }

        SubjectInfo subjectInfo = new SubjectInfo();

        subjectInfo.setId( subjectInfoBo.getId() );
        subjectInfo.setSubjectName( subjectInfoBo.getSubjectName() );
        subjectInfo.setSubjectDifficult( subjectInfoBo.getSubjectDifficult() );
        subjectInfo.setSettleName( subjectInfoBo.getSettleName() );
        subjectInfo.setSubjectType( subjectInfoBo.getSubjectType() );
        subjectInfo.setSubjectScore( subjectInfoBo.getSubjectScore() );
        subjectInfo.setSubjectParse( subjectInfoBo.getSubjectParse() );
        subjectInfo.setSubjectCount( subjectInfoBo.getSubjectCount() );

        return subjectInfo;
    }

    @Override
    public List<SubjectInfoBo> convertListInfoToBo(List<SubjectInfo> subjectInfoList) {
        if ( subjectInfoList == null ) {
            return null;
        }

        List<SubjectInfoBo> list = new ArrayList<SubjectInfoBo>( subjectInfoList.size() );
        for ( SubjectInfo subjectInfo : subjectInfoList ) {
            list.add( subjectInfoToSubjectInfoBo( subjectInfo ) );
        }

        return list;
    }

    @Override
    public SubjectInfoBo convertOptionToBo(SubjectOptionBo optionBo) {
        if ( optionBo == null ) {
            return null;
        }

        SubjectInfoBo subjectInfoBo = new SubjectInfoBo();

        subjectInfoBo.setSubjectAnswer( optionBo.getSubjectAnswer() );
        List<SubjectAnswerBo> list = optionBo.getOptionList();
        if ( list != null ) {
            subjectInfoBo.setOptionList( new ArrayList<SubjectAnswerBo>( list ) );
        }

        return subjectInfoBo;
    }

    @Override
    public SubjectInfoBo convertOptionAndInfoToBo(SubjectOptionBo optionBo, SubjectInfo subjectInfo) {
        if ( optionBo == null && subjectInfo == null ) {
            return null;
        }

        SubjectInfoBo subjectInfoBo = new SubjectInfoBo();

        if ( optionBo != null ) {
            subjectInfoBo.setSubjectAnswer( optionBo.getSubjectAnswer() );
            List<SubjectAnswerBo> list = optionBo.getOptionList();
            if ( list != null ) {
                subjectInfoBo.setOptionList( new ArrayList<SubjectAnswerBo>( list ) );
            }
        }
        if ( subjectInfo != null ) {
            subjectInfoBo.setId( subjectInfo.getId() );
            subjectInfoBo.setSubjectName( subjectInfo.getSubjectName() );
            subjectInfoBo.setSubjectDifficult( subjectInfo.getSubjectDifficult() );
            subjectInfoBo.setSettleName( subjectInfo.getSettleName() );
            subjectInfoBo.setSubjectType( subjectInfo.getSubjectType() );
            subjectInfoBo.setSubjectScore( subjectInfo.getSubjectScore() );
            subjectInfoBo.setSubjectParse( subjectInfo.getSubjectParse() );
            subjectInfoBo.setSubjectCount( subjectInfo.getSubjectCount() );
        }

        return subjectInfoBo;
    }

    protected SubjectInfoBo subjectInfoToSubjectInfoBo(SubjectInfo subjectInfo) {
        if ( subjectInfo == null ) {
            return null;
        }

        SubjectInfoBo subjectInfoBo = new SubjectInfoBo();

        subjectInfoBo.setId( subjectInfo.getId() );
        subjectInfoBo.setSubjectName( subjectInfo.getSubjectName() );
        subjectInfoBo.setSubjectDifficult( subjectInfo.getSubjectDifficult() );
        subjectInfoBo.setSettleName( subjectInfo.getSettleName() );
        subjectInfoBo.setSubjectType( subjectInfo.getSubjectType() );
        subjectInfoBo.setSubjectScore( subjectInfo.getSubjectScore() );
        subjectInfoBo.setSubjectParse( subjectInfo.getSubjectParse() );
        subjectInfoBo.setSubjectCount( subjectInfo.getSubjectCount() );

        return subjectInfoBo;
    }
}
