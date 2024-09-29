package com.jingdianyy.auth.domain.service.Impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.jingdianyy.auth.common.enums.AuthUserStatusEnum;
import com.jingdianyy.auth.common.enums.IsDeletedFlagEnum;
import com.jingdianyy.auth.domain.constants.AuthConstant;
import com.jingdianyy.auth.domain.convert.AuthUserBoConvert;
import com.jingdianyy.auth.domain.entity.AuthUserBo;
import com.jingdianyy.auth.domain.service.AuthUserDomainService;
import com.jingdianyy.auth.infra.basic.entity.AuthRole;
import com.jingdianyy.auth.infra.basic.entity.AuthUser;
import com.jingdianyy.auth.infra.basic.entity.AuthUserRole;
import com.jingdianyy.auth.infra.basic.service.AuthRoleService;
import com.jingdianyy.auth.infra.basic.service.AuthUserRoleService;
import com.jingdianyy.auth.infra.basic.service.AuthUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * 用户领域Impl
 */
@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthRoleService authRoleService;

    private String salt = "chicken";

    /**
     * 用户注册
     * @param authUserBo
     */
    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBo authUserBo){
        AuthUser authUser = AuthUserBoConvert.INSTANCE.convertBoToEntity(authUserBo);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = authUserService.insert(authUser);
        //建立一个初步的角色关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        authUserRoleService.insert(authUserRole);
        //要把当前用户的角色和权限都刷到我们的redis里
        return count > 0;
    }

    /**
     * 更新用户信息
     * @param authUserBo
     * @return
     */
    @Override
    public Boolean update(AuthUserBo authUserBo) {
        AuthUser authUser = AuthUserBoConvert.INSTANCE.convertBoToEntity(authUserBo);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = authUserService.update(authUser);
        //有任何更新都要与缓存进行同步修改
        return count > 0;
    }

    /**
     * 删除用户
     * @param authUserBo
     * @return
     */
    @Override
    public Boolean delete(AuthUserBo authUserBo) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBo.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = authUserService.update(authUser);
        //有任何更新都要与缓存进行同步修改
        return count > 0;
    }

    @Override
    public Boolean changeStatus(AuthUserBo authUserBo) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBo.getId());
        authUser.setStatus(authUserBo.getStatus());
        int count = authUserService.update(authUser);
        //有任何更新都要与缓存进行同步修改
        return count > 0;
    }
}
