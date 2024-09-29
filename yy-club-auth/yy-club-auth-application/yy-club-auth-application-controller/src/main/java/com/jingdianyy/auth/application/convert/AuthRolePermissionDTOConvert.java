package com.jingdianyy.auth.application.convert;

import com.jingdianyy.auth.application.dto.AuthPermissionDTO;
import com.jingdianyy.auth.application.dto.AuthRolePermissionDTO;
import com.jingdianyy.auth.domain.entity.AuthPermissionBo;
import com.jingdianyy.auth.domain.entity.AuthRolePermissionBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限bo转化器
 */
@Mapper
public interface AuthRolePermissionDTOConvert {

    AuthRolePermissionDTOConvert INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConvert.class);

    AuthRolePermissionBo convertDTOToBo(AuthRolePermissionDTO authRolePermissionDTO);

}
