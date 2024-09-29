package com.jingdianyy.auth.domain.service;

import com.jingdianyy.auth.domain.entity.AuthRoleBo;

public interface AuthRoleDomainService {
    Boolean add(AuthRoleBo authRoleBo);

    Boolean update(AuthRoleBo authRoleBo);

    Boolean delete(AuthRoleBo authRoleBo);
}
