package com.jingdianyy.subject.common.handler.subject;

import com.jingdianyy.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianyy.subject.domain.convert.JudgeSubjectConverter;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectJudge;
import com.jingdianyy.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 判断题的题目的策略类
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler {
    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypeEnum getSubjectType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBo subjectInfoBo) {
        //判断题题目的插入
        List<SubjectJudge> subjectJudgeList = new LinkedList<>();
        if(subjectInfoBo.getOptionList() != null){
            subjectInfoBo.getOptionList().forEach(option -> {
                SubjectJudge subjectJudge = JudgeSubjectConverter.INSTANCE.convertBoToJudge(option);
                subjectJudge.setSubjectId(subjectInfoBo.getId());
                subjectJudgeList.add(subjectJudge);
            });
        }
        subjectJudgeService.batchInsert(subjectJudgeList);

    }
}
