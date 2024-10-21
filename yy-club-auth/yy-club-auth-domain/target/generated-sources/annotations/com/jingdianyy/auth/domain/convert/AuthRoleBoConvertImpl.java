package com.jingdianyy.auth.domain.convert;

import com.jingdianyy.auth.domain.entity.AuthRoleBo;
import com.jingdianyy.auth.infra.basic.entity.AuthRole;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T14:36:25+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthRoleBoConvertImpl implements AuthRoleBoConvert {

    @Override
    public AuthRole convertBoToEntity(AuthRoleBo authRoleBo) {
        if ( authRoleBo == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setId( authRoleBo.getId() );
        authRole.setRoleName( authRoleBo.getRoleName() );
        authRole.setRoleKey( authRoleBo.getRoleKey() );

        return authRole;
    }
}
