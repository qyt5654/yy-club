package com.jingdianyy.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jingdianyy.subject.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.subject.domain.convert.SubjectCategoryConverter;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianyy.subject.infra.basic.entity.SubjectCategory;
import com.jingdianyy.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBo> queryCategory(SubjectCategoryBo subjectCategoryBo) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .converterBoToCategory(subjectCategoryBo);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBo> boList = SubjectCategoryConverter.INSTANCE
                .converterBoToCategory(subjectCategoryList);
        if(log.isDebugEnabled()){
            log.info("SubjectCategoryController.queryPrimaryCategory:{}",
                    JSON.toJSONString(boList));
        }
        return boList;
    }

    @Override
    public Boolean update(SubjectCategoryBo subjectCategoryBo) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .converterBoToCategory(subjectCategoryBo);

        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBo subjectCategoryBo) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .converterBoToCategory(subjectCategoryBo);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }
}
