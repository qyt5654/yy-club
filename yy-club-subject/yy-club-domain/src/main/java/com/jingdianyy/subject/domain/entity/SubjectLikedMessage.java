package com.jingdianyy.subject.domain.entity;

import com.jingdianyy.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目点赞消息
 *
 * @author jingdianyy
 * @since 2024-10-14 19:56:47
 */
@Data
public class SubjectLikedMessage extends PageInfo implements Serializable {

    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * 点赞人id
     */
    private String likeUserId;

    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;
}

