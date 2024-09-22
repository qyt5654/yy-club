package com.jingdianyy.subject.common.handler.subject;

import com.jingdianyy.subject.common.enums.SubjectInfoTypeEnum;
import com.jingdianyy.subject.domain.convert.RadioSubjectConverter;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.infra.basic.entity.SubjectRadio;
import com.jingdianyy.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 单选的题目的策略类
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getSubjectType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBo subjectInfoBo) {
        //单选题目的插入
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        if(subjectInfoBo.getOptionList() != null){
            subjectInfoBo.getOptionList().forEach(option -> {
                SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE.convertBoToRadio(option);
                subjectRadio.setSubjectId(subjectInfoBo.getId());
                subjectRadioList.add(subjectRadio);
            });
        }

        subjectRadioService.batchInsert(subjectRadioList);

    }
}