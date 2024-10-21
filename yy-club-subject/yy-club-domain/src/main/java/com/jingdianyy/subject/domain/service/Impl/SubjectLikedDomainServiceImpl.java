package com.jingdianyy.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.subject.common.enums.SubjectLikedStatusEnum;
import com.jingdianyy.subject.domain.convert.SubjectInfoConverter;
import com.jingdianyy.subject.domain.convert.SubjectLikedBOConverter;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.entity.SubjectLikedBO;
import com.jingdianyy.subject.domain.entity.SubjectLikedMessage;
import com.jingdianyy.subject.domain.redis.RedisUtil;
import com.jingdianyy.subject.domain.service.SubjectLikedDomainService;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;
import com.jingdianyy.subject.infra.basic.entity.SubjectLiked;
import com.jingdianyy.subject.infra.basic.service.SubjectInfoService;
import com.jingdianyy.subject.infra.basic.service.SubjectLikedService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 题目点赞表 领域service实现了
 *
 * @author jingdianyy
 * @since 2024-10-14 19:56:47
 */
@Service
@Slf4j
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {

    @Resource
    private SubjectLikedService subjectLikedService;
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 对应hash的key
     */
    private static final String SUBJECT_LIKED_KEY = "subject.liked";

    private static final String SUBJECT_LIKED_COUNT_KEY = "subject.liked.count";

    private static final String SUBJECT_LIKED_DETAIL_KEY = "subject.liked.count";

    @Override
    public void add(SubjectLikedBO subjectLikedBO) {
        Long subjectId = subjectLikedBO.getSubjectId();
        String likeUserId = subjectLikedBO.getLikeUserId();
        Integer status = subjectLikedBO.getStatus();
//        String hashKey = buildSubjectLikedKey(subjectId.toString(), likeUserId);
//        redisUtil.putHash(SUBJECT_LIKED_KEY, hashKey, status);

        SubjectLikedMessage subjectLikedMessage = new SubjectLikedMessage();
        subjectLikedMessage.setLikeUserId(likeUserId);
        subjectLikedMessage.setSubjectId(subjectId);
        subjectLikedMessage.setStatus(status);
        rocketMQTemplate.convertAndSend("subject-liked", JSON.toJSONString(subjectLikedMessage));

        String detailKey = SUBJECT_LIKED_DETAIL_KEY  + "." + subjectId + "." + likeUserId;
        String countKey = SUBJECT_LIKED_COUNT_KEY + "." + subjectId;
        if(SubjectLikedStatusEnum.LIKED.getCode() == status){
            redisUtil.increment(countKey, 1);
            redisUtil.set(detailKey, "1");
        }else{
            Integer count = redisUtil.getInt(countKey);
            if(Objects.isNull(count) || count <= 0){
                return;
            }
            redisUtil.increment(countKey, -1);
            redisUtil.del(detailKey);
        }
    }

    @Override
    public Boolean isLiked(String subjectId, String userId) {
        String detailKey = SUBJECT_LIKED_DETAIL_KEY  + "." + subjectId + "." + userId;
        return redisUtil.exist(detailKey);
    }

    @Override
    public Integer getLikedCount(String subjectId) {
        String countKey = SUBJECT_LIKED_COUNT_KEY + "." + subjectId;
        Integer count = redisUtil.getInt(countKey);
        if(Objects.isNull(count) || count <= 0){
            return 0;
        }
        return redisUtil.getInt(countKey);
    }

    /**
     * 构造hash中的key
     * @param subjectId
     * @param userId
     * @return
     */
    private String buildSubjectLikedKey(String subjectId, String userId){
        return subjectId + ":" + userId;
    }

    @Override
    public Boolean update(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        return subjectLikedService.update(subjectLiked) > 0;
    }

    @Override
    public Boolean delete(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = new SubjectLiked();
        subjectLiked.setId(subjectLikedBO.getId());
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLikedService.update(subjectLiked) > 0;
    }

    /**
     * 同步点赞数据
     */
    @Override
    public void syncLiked() {
        Map<Object, Object> subjectLikedMap = redisUtil.getHashAndDelete(SUBJECT_LIKED_KEY);
        if(log.isDebugEnabled()){
            log.info("syncLiked.subjectLikedMap:{}", JSON.toJSONString(subjectLikedMap));
        }
        if(MapUtils.isEmpty(subjectLikedMap)){
            return;
        }
        //批量同步到数据库
        List<SubjectLiked> subjectLikedList = new LinkedList<>();
        subjectLikedMap.forEach((key, value) -> {
            SubjectLiked subjectLiked = new SubjectLiked();
            String[] keyArr = key.toString().split(":");
            String subjectId = keyArr[0];
            String likedUser = keyArr[1];
            subjectLiked.setSubjectId(Long.valueOf(subjectId));
            subjectLiked.setLikeUserId(likedUser);
            subjectLiked.setStatus(Integer.valueOf(value.toString()));
            subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
            subjectLikedList.add(subjectLiked);
        });
        subjectLikedService.batchInsert(subjectLikedList);
    }

    /**
     * 查询我的点赞列表
     */
    @Override
    public PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO) {
        PageResult<SubjectLikedBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectLikedBO.getPageNo());
        pageResult.setPageSize(subjectLikedBO.getPageSize());
        int start = (subjectLikedBO.getPageNo() - 1) * subjectLikedBO.getPageSize();
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = subjectLikedService.countByCondition(subjectLiked);
        if(count == 0){
            return pageResult;
        }
        List<SubjectLiked> subjectLikedList = subjectLikedService.queryPage(subjectLiked, start, subjectLikedBO.getPageSize());
        List<SubjectLikedBO> subjectLikedBos = SubjectLikedBOConverter.INSTANCE.convertListInfoToBo(subjectLikedList);

        subjectLikedBos.forEach(info -> {
            SubjectInfo subjectInfo = subjectInfoService.queryById(info.getSubjectId());
            info.setSubjectName(subjectInfo.getSubjectName());
        });

        pageResult.setRecords(subjectLikedBos);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public void syncLikedByMsg(SubjectLikedBO subjectLikedBO) {
        List<SubjectLiked> subjectLikedList = new LinkedList<>();
        SubjectLiked subjectLiked = new SubjectLiked();
        subjectLiked.setSubjectId(subjectLikedBO.getSubjectId());
        subjectLiked.setLikeUserId(subjectLikedBO.getLikeUserId());
        subjectLiked.setStatus(subjectLikedBO.getStatus());
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectLikedList.add(subjectLiked);
        subjectLikedService.batchInsert(subjectLikedList);
    }

}
