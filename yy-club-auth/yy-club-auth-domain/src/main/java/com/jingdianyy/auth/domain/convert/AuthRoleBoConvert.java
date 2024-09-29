package com.jingdianyy.auth.domain.convert;

import com.jingdianyy.auth.domain.entity.AuthRoleBo;
import com.jingdianyy.auth.domain.entity.AuthUserBo;
import com.jingdianyy.auth.infra.basic.entity.AuthRole;
import com.jingdianyy.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleBoConvert {

    AuthRoleBoConvert INSTANCE = Mappers.getMapper(AuthRoleBoConvert.class);

    AuthRole convertBoToEntity(AuthRoleBo authRoleBo);

}
