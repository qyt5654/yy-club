package com.jingdianyy.auth.domain.service;


import cn.dev33.satoken.stp.SaTokenInfo;
import com.jingdianyy.auth.domain.entity.AuthUserBo;

/**
 * 用户领域service
 */
public interface AuthUserDomainService {

    /**
     * 注册
     * @param authUserBo
     */
    Boolean register(AuthUserBo authUserBo) throws Exception;

    /**
     * 更新用户信息
     * @param authUserBo
     * @return
     */
    Boolean update(AuthUserBo authUserBo);

    /**
     * 删除用户
     * @param authUserBo
     * @return
     */
    Boolean delete(AuthUserBo authUserBo);

    /**
     * 修改用户状态
     * @param authUserBo
     * @return
     */
    Boolean changeStatus(AuthUserBo authUserBo);

    SaTokenInfo doLogin(String validCode);

    /**
     * 获取用户信息接口
     * @param authUserBo
     * @return
     */
    AuthUserBo getUserInfo(AuthUserBo authUserBo);
}
