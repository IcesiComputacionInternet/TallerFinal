package com.icesi.backend.service;


import icesi.VirtualStore.dto.LoginDTO;
import icesi.VirtualStore.dto.TokenDTO;
import icesi.VirtualStore.model.Permission;

import java.util.List;
import java.util.UUID;

public interface LoginService {

    TokenDTO loginByEmail(LoginDTO loginDTO);

    TokenDTO loginByPhoneNumber(LoginDTO loginDTO);

    List<Permission> getPermissionsByRoleId(UUID role);

}
