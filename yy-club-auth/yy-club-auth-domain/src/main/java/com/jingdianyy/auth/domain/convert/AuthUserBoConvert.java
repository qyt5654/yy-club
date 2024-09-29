package com.jingdianyy.auth.domain.convert;

import com.jingdianyy.auth.domain.entity.AuthUserBo;
import com.jingdianyy.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserBoConvert {

    AuthUserBoConvert INSTANCE = Mappers.getMapper(AuthUserBoConvert.class);

    AuthUser convertBoToEntity(AuthUserBo authUserBo);

}
