package com.jingdianyy.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.nacos.api.utils.StringUtils;
import com.google.gson.Gson;
import com.jingdianyy.club.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.Role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getStrings(loginId, authPermissionPrefix);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getStrings(loginId, authRolePrefix);
    }

    private List<String> getStrings(Object loginId, String authRolePrefix) {
        String authKey = redisUtil.buildKey(authRolePrefix, loginId.toString());
        String authValue = redisUtil.get(authKey);
        if(StringUtils.isBlank(authValue)){
            return Collections.emptyList();
        }
        List<String> roleList = new Gson().fromJson(authValue, List.class);
        return roleList;
    }
}
