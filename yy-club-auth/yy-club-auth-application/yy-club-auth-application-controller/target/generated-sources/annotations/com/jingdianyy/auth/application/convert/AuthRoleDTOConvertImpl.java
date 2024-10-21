package com.jingdianyy.auth.application.convert;

import com.jingdianyy.auth.application.dto.AuthRoleDTO;
import com.jingdianyy.auth.domain.entity.AuthRoleBo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T14:36:28+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthRoleDTOConvertImpl implements AuthRoleDTOConvert {

    @Override
    public AuthRoleBo convertDTOToBo(AuthRoleDTO authRoleBo) {
        if ( authRoleBo == null ) {
            return null;
        }

        AuthRoleBo authRoleBo1 = new AuthRoleBo();

        authRoleBo1.setId( authRoleBo.getId() );
        authRoleBo1.setRoleName( authRoleBo.getRoleName() );
        authRoleBo1.setRoleKey( authRoleBo.getRoleKey() );

        return authRoleBo1;
    }
}
