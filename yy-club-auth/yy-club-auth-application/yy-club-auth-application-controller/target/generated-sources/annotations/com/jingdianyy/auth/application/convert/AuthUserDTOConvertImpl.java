package com.jingdianyy.auth.application.convert;

import com.jingdianyy.auth.domain.entity.AuthUserBo;
import com.jingdianyy.auth.entity.AuthUserDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T14:36:28+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthUserDTOConvertImpl implements AuthUserDTOConvert {

    @Override
    public AuthUserBo convertDTOToBo(AuthUserDTO authUserDTO) {
        if ( authUserDTO == null ) {
            return null;
        }

        AuthUserBo authUserBo = new AuthUserBo();

        authUserBo.setId( authUserDTO.getId() );
        authUserBo.setUserName( authUserDTO.getUserName() );
        authUserBo.setNickName( authUserDTO.getNickName() );
        authUserBo.setEmail( authUserDTO.getEmail() );
        authUserBo.setPhone( authUserDTO.getPhone() );
        authUserBo.setPassword( authUserDTO.getPassword() );
        authUserBo.setSex( authUserDTO.getSex() );
        authUserBo.setAvatar( authUserDTO.getAvatar() );
        authUserBo.setStatus( authUserDTO.getStatus() );
        authUserBo.setIntroduce( authUserDTO.getIntroduce() );
        authUserBo.setExtJson( authUserDTO.getExtJson() );

        return authUserBo;
    }

    @Override
    public AuthUserDTO convertBoToDTO(AuthUserBo authUserBo) {
        if ( authUserBo == null ) {
            return null;
        }

        AuthUserDTO authUserDTO = new AuthUserDTO();

        authUserDTO.setId( authUserBo.getId() );
        authUserDTO.setUserName( authUserBo.getUserName() );
        authUserDTO.setNickName( authUserBo.getNickName() );
        authUserDTO.setEmail( authUserBo.getEmail() );
        authUserDTO.setPhone( authUserBo.getPhone() );
        authUserDTO.setPassword( authUserBo.getPassword() );
        authUserDTO.setSex( authUserBo.getSex() );
        authUserDTO.setAvatar( authUserBo.getAvatar() );
        authUserDTO.setStatus( authUserBo.getStatus() );
        authUserDTO.setIntroduce( authUserBo.getIntroduce() );
        authUserDTO.setExtJson( authUserBo.getExtJson() );

        return authUserDTO;
    }
}
