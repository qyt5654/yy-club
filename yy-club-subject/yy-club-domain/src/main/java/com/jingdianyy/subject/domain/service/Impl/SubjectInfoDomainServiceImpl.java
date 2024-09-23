package com.jingdianyy.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.subject.domain.entity.SubjectOptionBo;
import com.jingdianyy.subject.domain.handler.subject.SubjectTypeHandler;
import com.jingdianyy.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jingdianyy.subject.domain.convert.SubjectInfoConverter;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.service.SubjectInfoDomainService;
import com.jingdianyy.subject.domain.service.SubjectLabelDomainService;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;
import com.jingdianyy.subject.infra.basic.entity.SubjectLabel;
import com.jingdianyy.subject.infra.basic.entity.SubjectMapping;
import com.jingdianyy.subject.infra.basic.service.SubjectInfoService;
import com.jingdianyy.subject.infra.basic.service.SubjectLabelService;
import com.jingdianyy.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;

    /**
     * 添加题目
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBo subjectInfoBo) {
        if (log.isDebugEnabled()) {
            log.debug("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBo));
        }
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBo);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectInfoService.insert(subjectInfo);
        subjectInfoBo.setId(subjectInfo.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfoBo.getSubjectType());
        handler.add(subjectInfoBo);
        List<SubjectMapping> subjectMappings = getSubjectMappings(subjectInfoBo, subjectInfo);
        subjectMappingService.batchInsert(subjectMappings);
    }

    /**
     * 封装List<SubjectMapping>数据
     * @param subjectInfoBo
     * @param subjectInfo
     * @return
     */
    private static List<SubjectMapping> getSubjectMappings(SubjectInfoBo subjectInfoBo, SubjectInfo subjectInfo) {
        List<Long> categoryIds = subjectInfoBo.getCategoryIds();
        List<Long> labelIds = subjectInfoBo.getLabelIds();
        List<SubjectMapping> subjectMappings = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setLabelId(labelId);
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
                subjectMappings.add(subjectMapping);
            });
        });
        return subjectMappings;
    }

    @Override
    public PageResult<SubjectInfoBo> getSubjectPage(SubjectInfoBo subjectInfoBo) {
        PageResult<SubjectInfoBo> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBo.getPageNo());
        pageResult.setPageSize(subjectInfoBo.getPageSize());
        int start = (subjectInfoBo.getPageNo() - 1) * subjectInfoBo.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBo);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBo.getLabelId(),
                subjectInfoBo.getCategoryId());
        if(count == 0){
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBo.getLabelId(),
                subjectInfoBo.getCategoryId(), start, subjectInfoBo.getPageSize());

        List<SubjectInfoBo> subjectInfoBos = SubjectInfoConverter.INSTANCE.convertListInfoToBo(subjectInfoList);

        pageResult.setRecords(subjectInfoBos);
        pageResult.setTotal(count);
        return pageResult;
    }

    /**
     * 查询题目信息
     * @param subjectInfoBo
     * @return
     */
    @Override
    public SubjectInfoBo getSubjectInfo(SubjectInfoBo subjectInfoBo) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBo.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBo optionBO = handler.query(subjectInfo.getId());

        SubjectInfoBo bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBo(optionBO, subjectInfo);
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());

        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());

        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());

        bo.setLabelNames(labelNameList);
//        bo.setLiked(subjectLikedDomainService.isLiked(subjectInfoBo.getId().toString(), LoginUtil.getLoginId()));
//        bo.setLikedCount(subjectLikedDomainService.getLikedCount(subjectInfoBO.getId().toString()));
//        assembleSubjectCursor(subjectInfoBO, bo);
        return bo;

    }
}
