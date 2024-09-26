package com.jingdianyy.subject.application.convert;

import com.jingdianyy.subject.application.dto.SubjectAnswerDTO;
import com.jingdianyy.subject.application.dto.SubjectInfoDTO;
import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T17:03:43+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class SubjectInfoDTOConverterImpl implements SubjectInfoDTOConverter {

    @Override
    public SubjectInfoBo converterDTOToBo(SubjectInfoDTO subjectInfoDTO) {
        if ( subjectInfoDTO == null ) {
            return null;
        }

        SubjectInfoBo subjectInfoBo = new SubjectInfoBo();

        subjectInfoBo.setId( subjectInfoDTO.getId() );
        subjectInfoBo.setSubjectName( subjectInfoDTO.getSubjectName() );
        subjectInfoBo.setSubjectDifficult( subjectInfoDTO.getSubjectDifficult() );
        subjectInfoBo.setSettleName( subjectInfoDTO.getSettleName() );
        subjectInfoBo.setSubjectType( subjectInfoDTO.getSubjectType() );
        subjectInfoBo.setSubjectScore( subjectInfoDTO.getSubjectScore() );
        subjectInfoBo.setSubjectParse( subjectInfoDTO.getSubjectParse() );
        subjectInfoBo.setSubjectAnswer( subjectInfoDTO.getSubjectAnswer() );
        subjectInfoBo.setCategoryIds( integerListToLongList( subjectInfoDTO.getCategoryIds() ) );
        subjectInfoBo.setLabelIds( integerListToLongList( subjectInfoDTO.getLabelIds() ) );
        List<String> list2 = subjectInfoDTO.getLabelNames();
        if ( list2 != null ) {
            subjectInfoBo.setLabelNames( new ArrayList<String>( list2 ) );
        }
        subjectInfoBo.setOptionList( subjectAnswerDTOListToSubjectAnswerBoList( subjectInfoDTO.getOptionList() ) );
        subjectInfoBo.setCategoryId( subjectInfoDTO.getCategoryId() );
        subjectInfoBo.setLabelId( subjectInfoDTO.getLabelId() );

        return subjectInfoBo;
    }

    @Override
    public SubjectInfoDTO converterBoToDTO(SubjectInfoBo subjectInfoBo) {
        if ( subjectInfoBo == null ) {
            return null;
        }

        SubjectInfoDTO subjectInfoDTO = new SubjectInfoDTO();

        subjectInfoDTO.setId( subjectInfoBo.getId() );
        subjectInfoDTO.setSubjectName( subjectInfoBo.getSubjectName() );
        subjectInfoDTO.setSubjectDifficult( subjectInfoBo.getSubjectDifficult() );
        subjectInfoDTO.setSettleName( subjectInfoBo.getSettleName() );
        subjectInfoDTO.setSubjectType( subjectInfoBo.getSubjectType() );
        subjectInfoDTO.setSubjectScore( subjectInfoBo.getSubjectScore() );
        subjectInfoDTO.setSubjectParse( subjectInfoBo.getSubjectParse() );
        subjectInfoDTO.setSubjectAnswer( subjectInfoBo.getSubjectAnswer() );
        subjectInfoDTO.setCategoryIds( longListToIntegerList( subjectInfoBo.getCategoryIds() ) );
        subjectInfoDTO.setLabelIds( longListToIntegerList( subjectInfoBo.getLabelIds() ) );
        subjectInfoDTO.setOptionList( subjectAnswerBoListToSubjectAnswerDTOList( subjectInfoBo.getOptionList() ) );
        List<String> list3 = subjectInfoBo.getLabelNames();
        if ( list3 != null ) {
            subjectInfoDTO.setLabelNames( new ArrayList<String>( list3 ) );
        }
        subjectInfoDTO.setCategoryId( subjectInfoBo.getCategoryId() );
        subjectInfoDTO.setLabelId( subjectInfoBo.getLabelId() );

        return subjectInfoDTO;
    }

    @Override
    public PageResult<SubjectInfoDTO> converterBoToPageResultDTO(PageResult<SubjectInfoBo> pageResult) {
        if ( pageResult == null ) {
            return null;
        }

        PageResult<SubjectInfoDTO> pageResult1 = new PageResult<SubjectInfoDTO>();

        pageResult1.setTotal( pageResult.getTotal() );
        pageResult1.setPageSize( pageResult.getPageSize() );
        pageResult1.setPageNo( pageResult.getPageNo() );
        pageResult1.setTotalPages( pageResult.getTotalPages() );
        pageResult1.setResult( subjectInfoBoListToSubjectInfoDTOList( pageResult.getResult() ) );
        pageResult1.setStart( pageResult.getStart() );
        pageResult1.setEnd( pageResult.getEnd() );

        return pageResult1;
    }

    protected List<Long> integerListToLongList(List<Integer> list) {
        if ( list == null ) {
            return null;
        }

        List<Long> list1 = new ArrayList<Long>( list.size() );
        for ( Integer integer : list ) {
            list1.add( integer.longValue() );
        }

        return list1;
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

    protected List<SubjectAnswerBo> subjectAnswerDTOListToSubjectAnswerBoList(List<SubjectAnswerDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerBo> list1 = new ArrayList<SubjectAnswerBo>( list.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : list ) {
            list1.add( subjectAnswerDTOToSubjectAnswerBo( subjectAnswerDTO ) );
        }

        return list1;
    }

    protected List<Integer> longListToIntegerList(List<Long> list) {
        if ( list == null ) {
            return null;
        }

        List<Integer> list1 = new ArrayList<Integer>( list.size() );
        for ( Long long1 : list ) {
            list1.add( long1.intValue() );
        }

        return list1;
    }

    protected SubjectAnswerDTO subjectAnswerBoToSubjectAnswerDTO(SubjectAnswerBo subjectAnswerBo) {
        if ( subjectAnswerBo == null ) {
            return null;
        }

        SubjectAnswerDTO subjectAnswerDTO = new SubjectAnswerDTO();

        subjectAnswerDTO.setOptionType( subjectAnswerBo.getOptionType() );
        subjectAnswerDTO.setOptionContent( subjectAnswerBo.getOptionContent() );
        subjectAnswerDTO.setIsCorrect( subjectAnswerBo.getIsCorrect() );

        return subjectAnswerDTO;
    }

    protected List<SubjectAnswerDTO> subjectAnswerBoListToSubjectAnswerDTOList(List<SubjectAnswerBo> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerDTO> list1 = new ArrayList<SubjectAnswerDTO>( list.size() );
        for ( SubjectAnswerBo subjectAnswerBo : list ) {
            list1.add( subjectAnswerBoToSubjectAnswerDTO( subjectAnswerBo ) );
        }

        return list1;
    }

    protected List<SubjectInfoDTO> subjectInfoBoListToSubjectInfoDTOList(List<SubjectInfoBo> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectInfoDTO> list1 = new ArrayList<SubjectInfoDTO>( list.size() );
        for ( SubjectInfoBo subjectInfoBo : list ) {
            list1.add( converterBoToDTO( subjectInfoBo ) );
        }

        return list1;
    }
}
