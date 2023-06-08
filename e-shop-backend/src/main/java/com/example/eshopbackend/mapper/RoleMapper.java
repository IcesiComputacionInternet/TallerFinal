package com.example.eshopbackend.mapper;

import com.example.eshopbackend.dto.RoleDTO;
import com.example.eshopbackend.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role fromRoleDTO(RoleDTO role);
    RoleDTO fromRole(Role role);
}
