package com.jingdianyy.subject.common.handler.subject;

import com.jingdianyy.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianyy.subject.domain.convert.BriefSubjectConverter;
import com.jingdianyy.subject.domain.convert.JudgeSubjectConverter;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectBrief;
import com.jingdianyy.subject.infra.basic.entity.SubjectJudge;
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
        List<SubjectBrief> subjectBriefList = new LinkedList<>();
        if(subjectInfoBo.getOptionList() != null){
            subjectInfoBo.getOptionList().forEach(option -> {
                SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToBrief(option);
                subjectBrief.setSubjectId(subjectInfoBo.getId());
                subjectBriefList.add(subjectBrief);
            });
        }
        subjectBriefService.batchInsert(subjectBriefList);

    }
}
