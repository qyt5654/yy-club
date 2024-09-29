package com.jingdianyy.auth.domain.convert;

import com.jingdianyy.auth.domain.entity.AuthPermissionBo;
import com.jingdianyy.auth.domain.entity.AuthRoleBo;
import com.jingdianyy.auth.infra.basic.entity.AuthPermission;
import com.jingdianyy.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionBoConvert {

    AuthPermissionBoConvert INSTANCE = Mappers.getMapper(AuthPermissionBoConvert.class);

    AuthPermission convertBoToEntity(AuthPermissionBo authPermissionBo);

}
