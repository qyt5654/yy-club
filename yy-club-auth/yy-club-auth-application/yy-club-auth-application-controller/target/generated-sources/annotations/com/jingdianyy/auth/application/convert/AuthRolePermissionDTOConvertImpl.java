package com.jingdianyy.auth.application.convert;

import com.jingdianyy.auth.application.dto.AuthRolePermissionDTO;
import com.jingdianyy.auth.domain.entity.AuthRolePermissionBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-11T14:36:27+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthRolePermissionDTOConvertImpl implements AuthRolePermissionDTOConvert {

    @Override
    public AuthRolePermissionBo convertDTOToBo(AuthRolePermissionDTO authRolePermissionDTO) {
        if ( authRolePermissionDTO == null ) {
            return null;
        }

        AuthRolePermissionBo authRolePermissionBo = new AuthRolePermissionBo();

        authRolePermissionBo.setId( authRolePermissionDTO.getId() );
        authRolePermissionBo.setRoleId( authRolePermissionDTO.getRoleId() );
        authRolePermissionBo.setPermissionId( authRolePermissionDTO.getPermissionId() );
        List<Long> list = authRolePermissionDTO.getPermissionIdList();
        if ( list != null ) {
            authRolePermissionBo.setPermissionIdList( new ArrayList<Long>( list ) );
        }

        return authRolePermissionBo;
    }
}
