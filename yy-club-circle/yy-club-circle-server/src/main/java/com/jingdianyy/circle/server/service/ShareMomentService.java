package com.jingdianyy.circle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingdianyy.circle.api.common.PageResult;
import com.jingdianyy.circle.api.req.GetShareMomentReq;
import com.jingdianyy.circle.api.req.RemoveShareMomentReq;
import com.jingdianyy.circle.api.req.SaveMomentCircleReq;
import com.jingdianyy.circle.api.vo.ShareMomentVO;
import com.jingdianyy.circle.server.entity.po.ShareMoment;

/**
 * <p>
 * 动态信息 服务类
 * </p>
 *
 * @author ChickenWing
 * @since 2024/05/16
 */
public interface ShareMomentService extends IService<ShareMoment> {

    Boolean saveMoment(SaveMomentCircleReq req);

    PageResult<ShareMomentVO> getMoments(GetShareMomentReq req);

    Boolean removeMoment(RemoveShareMomentReq req);

    void incrReplyCount(Long id, int count);

}
