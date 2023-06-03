package com.peliculas.grupo3.mapper;


import com.peliculas.grupo3.dto.RoleDTO;
import com.peliculas.grupo3.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

        Role fromRoleDTO(RoleDTO orderDTO);
}
