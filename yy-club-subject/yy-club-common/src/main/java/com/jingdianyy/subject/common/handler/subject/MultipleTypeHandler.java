package com.jingdianyy.subject.common.handler.subject;

import com.jingdianyy.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianyy.subject.domain.convert.JudgeSubjectConverter;
import com.jingdianyy.subject.domain.convert.MultipleSubjectConverter;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectJudge;
import com.jingdianyy.subject.infra.basic.entity.SubjectMultiple;
import com.jingdianyy.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 多选的题目的策略类
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getSubjectType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBo subjectInfoBo) {
        //多选题题目的插入
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        if(subjectInfoBo.getOptionList() != null){
            subjectInfoBo.getOptionList().forEach(option -> {
                SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convertBoToJudge(option);
                subjectMultiple.setSubjectId(subjectInfoBo.getId());
                subjectMultipleList.add(subjectMultiple);
            });
        }
        subjectMultipleService.batchInsert(subjectMultipleList);

    }
}
