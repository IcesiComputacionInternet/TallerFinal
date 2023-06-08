package com.icesi.backend.service;


import com.icesi.backend.DTO.LoginDTO;
import com.icesi.backend.DTO.TokenDTO;

import java.security.Permission;
import java.util.List;
import java.util.UUID;

public interface LoginService {

    TokenDTO loginByEmail(LoginDTO loginDTO);

    TokenDTO loginByPhoneNumber(LoginDTO loginDTO);

    List<Permission> getPermissionsByRoleId(UUID role);

}
