package com.jingdianyy.subject.domain.service.Impl;

import com.jingdianyy.subject.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.subject.domain.convert.SubjectLikedBOConverter;
import com.jingdianyy.subject.domain.entity.SubjectLikedBO;
import com.jingdianyy.subject.domain.service.SubjectLikedDomainService;
import com.jingdianyy.subject.infra.basic.entity.SubjectLiked;
import com.jingdianyy.subject.infra.basic.service.SubjectLikedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public Boolean add(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        return subjectLikedService.insert(subjectLiked) > 0;
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

}
