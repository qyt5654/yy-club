package com.jingdianyy.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jingdianyy.subject.domain.convert.SubjectCategoryConverter;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianyy.subject.infra.basic.entity.SubjectCategory;
import com.jingdianyy.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public void add(SubjectCategoryBo subjectCategorybo) {
        if(log.isDebugEnabled()){
            log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategorybo));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .converterBoToCategory(subjectCategorybo);
        subjectCategoryService.insert(subjectCategory);
    }
}
