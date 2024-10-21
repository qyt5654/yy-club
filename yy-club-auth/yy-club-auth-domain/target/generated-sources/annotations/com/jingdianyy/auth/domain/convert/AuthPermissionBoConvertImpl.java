package com.jingdianyy.auth.domain.convert;

import com.jingdianyy.auth.domain.entity.AuthPermissionBo;
import com.jingdianyy.auth.infra.basic.entity.AuthPermission;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T14:36:25+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthPermissionBoConvertImpl implements AuthPermissionBoConvert {

    @Override
    public AuthPermission convertBoToEntity(AuthPermissionBo authPermissionBo) {
        if ( authPermissionBo == null ) {
            return null;
        }

        AuthPermission authPermission = new AuthPermission();

        authPermission.setId( authPermissionBo.getId() );
        authPermission.setName( authPermissionBo.getName() );
        authPermission.setParentId( authPermissionBo.getParentId() );
        authPermission.setType( authPermissionBo.getType() );
        authPermission.setMenuUrl( authPermissionBo.getMenuUrl() );
        authPermission.setStatus( authPermissionBo.getStatus() );
        authPermission.setShow( authPermissionBo.getShow() );
        authPermission.setIcon( authPermissionBo.getIcon() );
        authPermission.setPermissionKey( authPermissionBo.getPermissionKey() );

        return authPermission;
    }
}
