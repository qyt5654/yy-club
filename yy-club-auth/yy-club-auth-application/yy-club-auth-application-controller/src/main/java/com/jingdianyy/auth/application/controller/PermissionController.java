package com.jingdianyy.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdianyy.auth.application.convert.AuthPermissionDTOConvert;
import com.jingdianyy.auth.application.dto.AuthPermissionDTO;
import com.jingdianyy.auth.entity.Result;
import com.jingdianyy.auth.domain.entity.AuthPermissionBo;
import com.jingdianyy.auth.domain.service.AuthPermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * 角色controller
 */
@RestController
@RequestMapping("/permission/")
@Slf4j
public class PermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;

    /**
     * 新增权限
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("PermissionController.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()), "权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(), "权限父id不能为空");
            AuthPermissionBo authPermissionBo = AuthPermissionDTOConvert.INSTANCE.convertDTOToBo(authPermissionDTO);
            return Result.ok(authPermissionDomainService.add(authPermissionBo));
        }catch (Exception e){
            log.error("PermissionController.add.error:{}", e.getMessage() ,e);
            return Result.fail("新增权限失败");
        }
    }

    /**
     * 修改权限
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("PermissionController.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限id不能为空");
            AuthPermissionBo authPermissionBo = AuthPermissionDTOConvert.INSTANCE.convertDTOToBo(authPermissionDTO);
            return Result.ok(authPermissionDomainService.update(authPermissionBo));
        }catch (Exception e){
            log.error("PermissionController.update.error:{}", e.getMessage() ,e);
            return Result.fail("修改权限失败");
        }
    }

    /**
     * 删除权限
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("PermissionController.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限id不能为空");
            AuthPermissionBo authPermissionBo = AuthPermissionDTOConvert.INSTANCE.convertDTOToBo(authPermissionDTO);
            return Result.ok(authPermissionDomainService.delete(authPermissionBo));
        }catch (Exception e){
            log.error("PermissionController.delete.error:{}", e.getMessage() ,e);
            return Result.fail("删除权限失败");
        }
    }

    /**
     * 查询用户权限
     * @param userName
     * @return
     */
    @RequestMapping("getPermission")
    public Result<List<String>> getPermission(String userName) {
        try{
            log.info("PermissionController.getPermission.userName:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户id不能为空");
            return Result.ok(authPermissionDomainService.getPermission(userName));
        }catch (Exception e){
            log.error("PermissionController.getPermission.error:{}", e.getMessage() ,e);
            return Result.fail("查询用户权限失败");
        }
    }

}
