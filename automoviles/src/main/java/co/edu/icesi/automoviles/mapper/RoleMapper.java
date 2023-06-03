package co.edu.icesi.automoviles.mapper;

import org.mapstruct.Mapper;

import co.edu.icesi.automoviles.dto.RoleCreateDTO;
import co.edu.icesi.automoviles.dto.RoleShowDTO;
import co.edu.icesi.automoviles.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role fromRoleCreateDTO(RoleCreateDTO roleCreateDTO);
    RoleShowDTO fromRoleToRoleShowDTO(Role role);
}
