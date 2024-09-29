package com.jingdianyy.auth.application.convert;

import com.jingdianyy.auth.application.dto.AuthPermissionDTO;
import com.jingdianyy.auth.application.dto.AuthRoleDTO;
import com.jingdianyy.auth.domain.entity.AuthPermissionBo;
import com.jingdianyy.auth.domain.entity.AuthRoleBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限bo转化器
 */
@Mapper
public interface AuthPermissionDTOConvert {

    AuthPermissionDTOConvert INSTANCE = Mappers.getMapper(AuthPermissionDTOConvert.class);

    AuthPermissionBo convertDTOToBo(AuthPermissionDTO authPermissionDTO);

}
