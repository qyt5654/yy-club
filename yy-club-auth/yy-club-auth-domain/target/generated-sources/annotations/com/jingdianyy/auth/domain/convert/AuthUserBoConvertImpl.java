package com.jingdianyy.auth.domain.convert;

import com.jingdianyy.auth.domain.entity.AuthUserBo;
import com.jingdianyy.auth.infra.basic.entity.AuthUser;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T14:36:25+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthUserBoConvertImpl implements AuthUserBoConvert {

    @Override
    public AuthUser convertBoToEntity(AuthUserBo authUserBo) {
        if ( authUserBo == null ) {
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setId( authUserBo.getId() );
        authUser.setUserName( authUserBo.getUserName() );
        authUser.setNickName( authUserBo.getNickName() );
        authUser.setEmail( authUserBo.getEmail() );
        authUser.setPhone( authUserBo.getPhone() );
        authUser.setPassword( authUserBo.getPassword() );
        authUser.setSex( authUserBo.getSex() );
        authUser.setAvatar( authUserBo.getAvatar() );
        authUser.setStatus( authUserBo.getStatus() );
        authUser.setIntroduce( authUserBo.getIntroduce() );
        authUser.setExtJson( authUserBo.getExtJson() );

        return authUser;
    }

    @Override
    public AuthUserBo convertEntityToBo(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        AuthUserBo authUserBo = new AuthUserBo();

        authUserBo.setId( authUser.getId() );
        authUserBo.setUserName( authUser.getUserName() );
        authUserBo.setNickName( authUser.getNickName() );
        authUserBo.setEmail( authUser.getEmail() );
        authUserBo.setPhone( authUser.getPhone() );
        authUserBo.setPassword( authUser.getPassword() );
        authUserBo.setSex( authUser.getSex() );
        authUserBo.setAvatar( authUser.getAvatar() );
        authUserBo.setStatus( authUser.getStatus() );
        authUserBo.setIntroduce( authUser.getIntroduce() );
        authUserBo.setExtJson( authUser.getExtJson() );

        return authUserBo;
    }
}
