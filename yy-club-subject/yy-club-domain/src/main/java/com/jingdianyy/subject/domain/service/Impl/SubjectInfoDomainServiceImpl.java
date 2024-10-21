package com.jingdianyy.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.subject.common.util.IdWorkerUtil;
import com.jingdianyy.subject.common.util.LoginUtil;
import com.jingdianyy.subject.domain.entity.SubjectOptionBo;
import com.jingdianyy.subject.domain.handler.subject.SubjectTypeHandler;
import com.jingdianyy.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jingdianyy.subject.domain.convert.SubjectInfoConverter;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.redis.RedisUtil;
import com.jingdianyy.subject.domain.service.SubjectInfoDomainService;
import com.jingdianyy.subject.domain.service.SubjectLikedDomainService;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfoEs;
import com.jingdianyy.subject.infra.basic.entity.SubjectLabel;
import com.jingdianyy.subject.infra.basic.entity.SubjectMapping;
import com.jingdianyy.subject.infra.basic.service.SubjectEsService;
import com.jingdianyy.subject.infra.basic.service.SubjectInfoService;
import com.jingdianyy.subject.infra.basic.service.SubjectLabelService;
import com.jingdianyy.subject.infra.basic.service.SubjectMappingService;
import com.jingdianyy.subject.infra.entity.UserInfo;
import com.jingdianyy.subject.infra.rpc.UserRPC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
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
    @Resource
    private SubjectEsService subjectEsService;
    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;
    @Resource
    private UserRPC userRPC;
    @Resource
    private RedisUtil redisUtil;

    private static final String RANK_KEY = "subject_rank";

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
        //同步到es
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setDocId(new IdWorkerUtil(1, 1, 1).nextId());
        subjectInfoEs.setSubjectId(subjectInfoBo.getId());
        subjectInfoEs.setSubjectAnswer(subjectInfoBo.getSubjectAnswer());
        subjectInfoEs.setCreateTime(new Date().getTime());
        subjectInfoEs.setCreateUser("yyppcc");
        subjectInfoEs.setSubjectName(subjectInfo.getSubjectName());
        subjectInfoEs.setSubjectType(subjectInfo.getSubjectType());
        subjectEsService.insert(subjectInfoEs);
        //放入redis记录排行榜
        redisUtil.addScore(RANK_KEY, LoginUtil.getLoginId(), 1);
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
        //1.查询subject_info表
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBo.getId());

        //2.查询对应题目表-->拿到题目答案选项
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBo optionBO = handler.query(subjectInfo.getId());
        SubjectInfoBo bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBo(optionBO, subjectInfo);

        //3.查询subject_mapping表
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);

        //4.拿到labelName
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelNames(labelNameList);
        bo.setLiked(subjectLikedDomainService.isLiked(subjectInfoBo.getId().toString(), LoginUtil.getLoginId()));
        bo.setLikedCount(subjectLikedDomainService.getLikedCount(subjectInfoBo.getId().toString()));
        assembleSubjectCursor(subjectInfoBo, bo);
        return bo;
    }

    private void assembleSubjectCursor(SubjectInfoBo subjectInfoBo, SubjectInfoBo bo){
        Long categoryId = subjectInfoBo.getCategoryId();
        Long labelId = subjectInfoBo.getLabelId();
        Long subjectId = subjectInfoBo.getId();
        if(Objects.isNull(categoryId) || Objects.isNull(labelId)){
            return;
        }
        Long nextSubjectId = subjectInfoService.querySubjectIdCursor(subjectId, categoryId, labelId, 1);
        bo.setNextSubjectId(nextSubjectId);
        Long lastSubjectId = subjectInfoService.querySubjectIdCursor(subjectId, categoryId, labelId, 0);
        bo.setLastSubjectId(lastSubjectId);
    }

    /**
     * 全文检索
     * @param subjectInfoBo
     * @return
     */
    @Override
    public PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectInfoBo subjectInfoBo) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setKeyWord(subjectInfoBo.getKeyWord());
        subjectInfoEs.setPageSize(subjectInfoBo.getPageSize());
        subjectInfoEs.setPageNo(subjectInfoBo.getPageNo());
        return subjectEsService.querySubjectList(subjectInfoEs);
    }

    /**
     * 获取题目贡献榜
     * @return
     */
    @Override
    public List<SubjectInfoBo> getContributeList() {
        Set<ZSetOperations.TypedTuple<String>> typeTuples = redisUtil.rankWithScore(RANK_KEY, 0, 5);
        if(log.isDebugEnabled()){
            log.info("getContributeList.typeTuples:{}", JSON.toJSONString(typeTuples));
        }

        if(CollectionUtils.isEmpty(typeTuples)){
            return Collections.emptyList();
        }
        List<SubjectInfoBo> boList = new LinkedList<>();
        typeTuples.forEach(rank -> {
            SubjectInfoBo subjectInfoBo = new SubjectInfoBo();
            subjectInfoBo.setSubjectCount(rank.getScore().intValue());
            UserInfo userInfo = userRPC.getUserInfo(rank.getValue());
            subjectInfoBo.setCreateUser(userInfo.getNickName());
            subjectInfoBo.setCreateUserAvatar(userInfo.getAvatar());
            boList.add(subjectInfoBo);
        });
        return boList;

        /**
         * 数据库查询
         */
//        List<SubjectInfo> subjectInfoList = subjectInfoService.getContributeCount();
//        if(CollectionUtils.isEmpty(subjectInfoList)){
//            return Collections.emptyList();
//        }
//        List<SubjectInfoBo> boList = new LinkedList<>();
//        subjectInfoList.forEach(subjectInfo -> {
//            SubjectInfoBo subjectInfoBo = new SubjectInfoBo();
//            subjectInfoBo.setSubjectCount(subjectInfo.getSubjectCount());
//            UserInfo userInfo = userRPC.getUserInfo(subjectInfo.getCreatedBy());
//            subjectInfoBo.setCreateUser(userInfo.getNickName());
//            subjectInfoBo.setCreateUserAvatar(userInfo.getAvatar());
//            boList.add(subjectInfoBo);
//        });
//        return boList;
    }
}
