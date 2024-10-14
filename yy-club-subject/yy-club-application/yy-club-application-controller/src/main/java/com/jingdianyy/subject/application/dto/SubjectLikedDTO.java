package com.jingdianyy.subject.application.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 题目点赞表 dto
 *
 * @author jingdianyy
 * @since 2024-10-14 19:56:47
 */
@Data
public class SubjectLikedDTO implements Serializable {

    /**
     * 主键
     */
    private Long id;

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

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDeleted;

}
