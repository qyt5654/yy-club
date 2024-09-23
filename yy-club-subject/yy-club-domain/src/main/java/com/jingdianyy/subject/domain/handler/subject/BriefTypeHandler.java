package com.jingdianyy.subject.domain.handler.subject;

import com.jingdianyy.subject.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianyy.subject.domain.convert.BriefSubjectConverter;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.entity.SubjectOptionBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectBrief;
import com.jingdianyy.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 简答题的题目的策略类
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler {
    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getSubjectType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBo subjectInfoBo) {
        //简答题题目的插入
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBo);
        subjectBrief.setSubjectId(subjectInfoBo.getId());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBo query(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBo subjectOptionBo = new SubjectOptionBo();
        subjectOptionBo.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBo;
    }
}
