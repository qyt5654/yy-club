package com.jingdianyy.auth.api;

import com.jingdianyy.auth.entity.AuthUserDTO;
import com.jingdianyy.auth.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户服务feign
 */
@FeignClient("yy-club-auth-dev")
public interface UserFeignService {

    @RequestMapping("/user/getUserInfo")
    Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);

    @RequestMapping("/user/listByIds")
    Result<List<AuthUserDTO>> listUserInfoByIds(@RequestBody List<String> userNameList);

}
