package com.jingdianyy.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jingdianyy.subject.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.subject.domain.config.CustomNameThreadFactory;
import com.jingdianyy.subject.domain.convert.SubjectCategoryConverter;
import com.jingdianyy.subject.domain.convert.SubjectLabelConverter;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.domain.entity.SubjectLabelBo;
import com.jingdianyy.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianyy.subject.domain.util.CacheUtil;
import com.jingdianyy.subject.infra.basic.entity.SubjectCategory;
import com.jingdianyy.subject.infra.basic.entity.SubjectLabel;
import com.jingdianyy.subject.infra.basic.entity.SubjectMapping;
import com.jingdianyy.subject.infra.basic.service.SubjectCategoryService;
import com.jingdianyy.subject.infra.basic.service.impl.SubjectLabelServiceImpl;
import com.jingdianyy.subject.infra.basic.service.impl.SubjectMappingServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;
    @Resource
    private SubjectMappingServiceImpl subjectMappingService;
    @Resource
    private SubjectLabelServiceImpl subjectLabelService;
    @Resource
    private ThreadPoolExecutor labelThreadPool;
    @Resource
    private CacheUtil cacheUtil;

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
        boList.forEach(bo->{
            Integer subjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(subjectCount);
        });
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

    @Override
    @SneakyThrows
    public List<SubjectCategoryBo> queryCategoryAndLabel(SubjectCategoryBo subjectCategoryBo){
        //查询当前大类下所有分类
        String cacheKey = "categoryAndLabel." + subjectCategoryBo.getId();
        return cacheUtil.getResult(cacheKey, SubjectCategoryBo.class, (key) -> getSubjectCategoryBos(subjectCategoryBo.getId()));
    }

    private List<SubjectCategoryBo> getSubjectCategoryBos(Long categoryId) {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(categoryId);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        List<SubjectCategory> subjectCategories = subjectCategoryService.queryCategory(subjectCategory);
        if(log.isDebugEnabled()){
            log.info("SubjectCategoryController.queryCategoryAndLabel.subjectCategories:{}",
                    JSON.toJSONString(subjectCategories));
        }

        List<SubjectCategoryBo> categoryBoList = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategories);

        Map<Long, List<SubjectLabelBo>> map = new HashMap<>();
        List<CompletableFuture<Map<Long, List<SubjectLabelBo>>>> completableFutureList = categoryBoList.stream().map(category ->
                CompletableFuture.supplyAsync(() -> getSubjectLabelBos(category),
                        labelThreadPool)
        ).collect(Collectors.toList());

        completableFutureList.forEach(future->{
            try {
                Map<Long, List<SubjectLabelBo>> resultMap = future.get();
                map.putAll(resultMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        categoryBoList.forEach(categoryBo->{
            categoryBo.setLabelBoList(map.get(categoryBo.getId()));
        });

        return categoryBoList;
    }

    private Map<Long, List<SubjectLabelBo>> getSubjectLabelBos(SubjectCategoryBo category) {
        Map<Long, List<SubjectLabelBo>> longListMap = new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(category.getId());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
            if (CollectionUtils.isEmpty(mappingList)) {
                return null;
            }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabels = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBo> subjectLabelBos = SubjectLabelConverter.INSTANCE.convertListToBoList(subjectLabels);
        longListMap.put(category.getId(), subjectLabelBos);
        return longListMap;
    }
}
