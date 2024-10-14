package com.jingdianyy.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdianyy.auth.application.convert.AuthRolePermissionDTOConvert;
import com.jingdianyy.auth.application.dto.AuthRolePermissionDTO;
import com.jingdianyy.auth.entity.Result;
import com.jingdianyy.auth.domain.entity.AuthRolePermissionBo;
import com.jingdianyy.auth.domain.service.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限controller
 */
@RestController
@RequestMapping("/rolePermission/")
@Slf4j
public class RolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 新增角色权限关联关系
     * @param authRolePermissionDTO
     * @return
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try{
            if(log.isDebugEnabled()){
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限id不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色id不能为空");
            AuthRolePermissionBo authRolePermissionBo = AuthRolePermissionDTOConvert.INSTANCE.convertDTOToBo(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBo));
        }catch (Exception e){
            log.error("RolePermissionController.add.error:{}", e.getMessage() ,e);
            return Result.fail("新增角色权限关联关系失败");
        }
    }
}
