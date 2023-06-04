package co.icesi.automoviles.mapper;

import org.mapstruct.Mapper;

import co.icesi.automoviles.dto.RoleCreateDTO;
import co.icesi.automoviles.dto.RoleShowDTO;
import co.icesi.automoviles.model.Role;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role fromRoleCreateDTOToRole(RoleCreateDTO roleCreateDTO);
    RoleShowDTO fromRoleToRoleShowDTO(Role role);
}