package com.jingdianyy.auth.application.convert;

import com.jingdianyy.auth.application.dto.AuthUserDTO;
import com.jingdianyy.auth.domain.entity.AuthUserBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户dto转化器
 */
@Mapper
public interface AuthUserDTOConvert {

    AuthUserDTOConvert INSTANCE = Mappers.getMapper(AuthUserDTOConvert.class);

    AuthUserBo convertDTOToBo(AuthUserDTO authUserDTO);

}
