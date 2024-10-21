package com.jingdianyy.auth.application.convert;

import com.jingdianyy.auth.application.dto.AuthPermissionDTO;
import com.jingdianyy.auth.domain.entity.AuthPermissionBo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T14:36:28+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthPermissionDTOConvertImpl implements AuthPermissionDTOConvert {

    @Override
    public AuthPermissionBo convertDTOToBo(AuthPermissionDTO authPermissionDTO) {
        if ( authPermissionDTO == null ) {
            return null;
        }

        AuthPermissionBo authPermissionBo = new AuthPermissionBo();

        authPermissionBo.setId( authPermissionDTO.getId() );
        authPermissionBo.setName( authPermissionDTO.getName() );
        authPermissionBo.setParentId( authPermissionDTO.getParentId() );
        authPermissionBo.setType( authPermissionDTO.getType() );
        authPermissionBo.setMenuUrl( authPermissionDTO.getMenuUrl() );
        authPermissionBo.setStatus( authPermissionDTO.getStatus() );
        authPermissionBo.setShow( authPermissionDTO.getShow() );
        authPermissionBo.setIcon( authPermissionDTO.getIcon() );
        authPermissionBo.setPermissionKey( authPermissionDTO.getPermissionKey() );

        return authPermissionBo;
    }
}
