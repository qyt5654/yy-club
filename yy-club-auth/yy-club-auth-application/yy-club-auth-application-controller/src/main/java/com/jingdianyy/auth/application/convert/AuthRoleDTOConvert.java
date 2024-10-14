package com.jingdianyy.auth.application.convert;

import com.jingdianyy.auth.application.dto.AuthRoleDTO;
import com.jingdianyy.auth.domain.entity.AuthRoleBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色dto转化器
 */
@Mapper
public interface AuthRoleDTOConvert {

    AuthRoleDTOConvert INSTANCE = Mappers.getMapper(AuthRoleDTOConvert.class);

    AuthRoleBo convertDTOToBo(AuthRoleDTO authRoleBo);

}
