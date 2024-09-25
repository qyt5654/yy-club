package com.jingdianyy.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jingdianyy.subject.common.enums.CategoryTypeEnum;
import com.jingdianyy.subject.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.subject.domain.convert.SubjectCategoryConverter;
import com.jingdianyy.subject.domain.convert.SubjectLabelConverter;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.domain.entity.SubjectLabelBo;
import com.jingdianyy.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianyy.subject.domain.service.SubjectLabelDomainService;
import com.jingdianyy.subject.infra.basic.entity.SubjectCategory;
import com.jingdianyy.subject.infra.basic.entity.SubjectLabel;
import com.jingdianyy.subject.infra.basic.entity.SubjectMapping;
import com.jingdianyy.subject.infra.basic.service.SubjectCategoryService;
import com.jingdianyy.subject.infra.basic.service.SubjectLabelService;
import com.jingdianyy.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectCategoryService subjectCategoryService;

    /**
     * 添加表签
     * @param subjectLabelBo
     * @return
     */
    @Override
    public Boolean add(SubjectLabelBo subjectLabelBo) {
        if (log.isDebugEnabled()) {
            log.debug("SubjectLabelDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectLabelBo));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBo);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = subjectLabelService.insert(subjectLabel);
        return count > 0;
    }

    /**
     * 修改标签
     * @param subjectLabelBo
     * @return
     */
    @Override
    public Boolean update(SubjectLabelBo subjectLabelBo) {
        if (log.isDebugEnabled()) {
            log.debug("SubjectLabelDomainServiceImpl.update.bo:{}", JSON.toJSONString(subjectLabelBo));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBoToLabel(subjectLabelBo);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    /**
     * 删除标签
     * @param subjectLabelBo
     * @return
     */
    @Override
    public Boolean delete(SubjectLabelBo subjectLabelBo) {
        if (log.isDebugEnabled()) {
            log.debug("SubjectLabelDomainServiceImpl.delete.bo:{}", JSON.toJSONString(subjectLabelBo));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBoToLabel(subjectLabelBo);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public List<SubjectLabelBo> queryLabelByCategoryId(SubjectLabelBo subjectLabelBo) {
        //如果当前分类是一级分类则查询所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBo.getCategoryId());
        if(CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()){
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setCategoryId(subjectLabelBo.getCategoryId());
            subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
            List<SubjectLabel> labelList = subjectLabelService.queryByCondtion(subjectLabel);
            return SubjectLabelConverter.INSTANCE.convertListToBoList(labelList);
        }
        Long categoryId = subjectLabelBo.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        List<SubjectMapping> subjectMappings = subjectMappingService.queryLabelId(subjectMapping);
        if(CollectionUtils.isEmpty(subjectMappings)){
            return Collections.emptyList();
        }
        List<Long> labelIdList = subjectMappings.stream().map(item -> item.getLabelId()).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBo> subjectLabelBos = new ArrayList<>();
        labelList.forEach(label -> {
            SubjectLabelBo bo = new SubjectLabelBo();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setCategoryId(categoryId);
            bo.setSortNum(label.getSortNum());
            subjectLabelBos.add(bo);
        });
        return subjectLabelBos;
    }
}
