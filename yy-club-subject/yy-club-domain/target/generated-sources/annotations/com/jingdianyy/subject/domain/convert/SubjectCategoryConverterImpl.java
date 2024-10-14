package com.jingdianyy.subject.domain.convert;

import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectCategory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T17:33:48+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class SubjectCategoryConverterImpl implements SubjectCategoryConverter {

    @Override
    public SubjectCategory converterBoToCategory(SubjectCategoryBo subjectCategoryBo) {
        if ( subjectCategoryBo == null ) {
            return null;
        }

        SubjectCategory subjectCategory = new SubjectCategory();

        subjectCategory.setId( subjectCategoryBo.getId() );
        subjectCategory.setCategoryName( subjectCategoryBo.getCategoryName() );
        subjectCategory.setCategoryType( subjectCategoryBo.getCategoryType() );
        subjectCategory.setImageUrl( subjectCategoryBo.getImageUrl() );
        subjectCategory.setParentId( subjectCategoryBo.getParentId() );

        return subjectCategory;
    }

    @Override
    public List<SubjectCategoryBo> converterBoToCategory(List<SubjectCategory> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<SubjectCategoryBo> list = new ArrayList<SubjectCategoryBo>( categoryList.size() );
        for ( SubjectCategory subjectCategory : categoryList ) {
            list.add( subjectCategoryToSubjectCategoryBo( subjectCategory ) );
        }

        return list;
    }

    protected SubjectCategoryBo subjectCategoryToSubjectCategoryBo(SubjectCategory subjectCategory) {
        if ( subjectCategory == null ) {
            return null;
        }

        SubjectCategoryBo subjectCategoryBo = new SubjectCategoryBo();

        subjectCategoryBo.setId( subjectCategory.getId() );
        subjectCategoryBo.setCategoryName( subjectCategory.getCategoryName() );
        subjectCategoryBo.setCategoryType( subjectCategory.getCategoryType() );
        subjectCategoryBo.setImageUrl( subjectCategory.getImageUrl() );
        subjectCategoryBo.setParentId( subjectCategory.getParentId() );

        return subjectCategoryBo;
    }
}
