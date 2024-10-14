package com.jingdianyy.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdianyy.auth.application.convert.AuthUserDTOConvert;
import com.jingdianyy.auth.entity.Result;
import com.jingdianyy.auth.domain.entity.AuthUserBo;
import com.jingdianyy.auth.domain.service.AuthUserDomainService;
import com.jingdianyy.auth.entity.AuthUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user/")
@RestController
@Slf4j
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    /**
     * 用户注册接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);

            AuthUserBo authUserBo = AuthUserDTOConvert.INSTANCE.convertDTOToBo(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBo));
        }catch (Exception e){
            log.error("UserController.register.error:{}", e.getMessage() ,e);
            return Result.fail("注册失败");
        }

    }

    /**
     * 修改用户信息接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);

            AuthUserBo authUserBo = AuthUserDTOConvert.INSTANCE.convertDTOToBo(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBo));
        }catch (Exception e){
            log.error("UserController.update.error:{}", e.getMessage() ,e);
            return Result.fail("更新用户信息失败");
        }

    }
    private void checkUserInfo(AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮箱地址不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空");
    }

    /**
     * 获取用户信息接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            AuthUserBo authUserBo = AuthUserDTOConvert.INSTANCE.convertDTOToBo(authUserDTO);
            AuthUserDTO authUserDTO1 = AuthUserDTOConvert.INSTANCE.convertBoToDTO(authUserDomainService.getUserInfo(authUserBo));
            return Result.ok(authUserDTO1);
        }catch (Exception e){
            log.error("UserController.getUserInfo.error:{}", e.getMessage() ,e);
            return Result.fail("获取用户信息失败");
        }

    }

    /**
     * 用户退出接口
     * @param userName
     * @return
     */
    @RequestMapping("logOut")
    public Result logOut(@RequestParam String userName) {
        try{
            log.info("UserController.logOut.userName:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名不能为空");
            StpUtil.logout(userName);
            return Result.ok();
        }catch (Exception e){
            log.error("UserController.logOut.error:{}", e.getMessage() ,e);
            return Result.fail("用户登出失败");
        }

    }

    /**
     * 删除用户接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBo authUserBo = AuthUserDTOConvert.INSTANCE.convertDTOToBo(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBo));
        }catch (Exception e){
            log.error("UserController.delete.error:{}", e.getMessage() ,e);
            return Result.fail("删除用户失败");
        }
    }

    /**
     * 用户启用/禁用接口
     * @param authUserDTO
     * @return
     */
    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            AuthUserBo authUserBo = AuthUserDTOConvert.INSTANCE.convertDTOToBo(authUserDTO);
            return Result.ok(authUserDomainService.changeStatus(authUserBo));
        }catch (Exception e){
            log.error("UserController.changeStatus.error:{}", e.getMessage() ,e);
            return Result.fail("用户禁用或启用失败");
        }
    }

    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try{
            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "验证码不能为空!");
            return Result.ok(authUserDomainService.doLogin(validCode));
        }catch (Exception e){
            log.error("UserController.doLogin.error:{}", e.getMessage() ,e);
            return Result.fail("用户登录失败");
        }


    }

    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
    
}
