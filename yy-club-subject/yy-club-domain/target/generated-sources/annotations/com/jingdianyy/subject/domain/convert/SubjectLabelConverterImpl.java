package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectLabelBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectLabel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-25T21:36:51+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class SubjectLabelConverterImpl implements SubjectLabelConverter {

    @Override
    public SubjectLabel convertBoToLabel(SubjectLabelBo subjectLabelBo) {
        if ( subjectLabelBo == null ) {
            return null;
        }

        SubjectLabel subjectLabel = new SubjectLabel();

        subjectLabel.setId( subjectLabelBo.getId() );
        subjectLabel.setLabelName( subjectLabelBo.getLabelName() );
        subjectLabel.setSortNum( subjectLabelBo.getSortNum() );
        subjectLabel.setCategoryId( subjectLabelBo.getCategoryId() );

        return subjectLabel;
    }

    @Override
    public List<SubjectLabelBo> convertListToBoList(List<SubjectLabel> subjectLabelList) {
        if ( subjectLabelList == null ) {
            return null;
        }

        List<SubjectLabelBo> list = new ArrayList<SubjectLabelBo>( subjectLabelList.size() );
        for ( SubjectLabel subjectLabel : subjectLabelList ) {
            list.add( subjectLabelToSubjectLabelBo( subjectLabel ) );
        }

        return list;
    }

    protected SubjectLabelBo subjectLabelToSubjectLabelBo(SubjectLabel subjectLabel) {
        if ( subjectLabel == null ) {
            return null;
        }

        SubjectLabelBo subjectLabelBo = new SubjectLabelBo();

        subjectLabelBo.setId( subjectLabel.getId() );
        subjectLabelBo.setLabelName( subjectLabel.getLabelName() );
        subjectLabelBo.setSortNum( subjectLabel.getSortNum() );
        subjectLabelBo.setCategoryId( subjectLabel.getCategoryId() );

        return subjectLabelBo;
    }
}
