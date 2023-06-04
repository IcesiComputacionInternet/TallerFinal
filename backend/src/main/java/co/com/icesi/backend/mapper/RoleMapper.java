package co.com.icesi.backend.mapper;

import co.com.icesi.backend.dto.request.RoleDTO;
import co.com.icesi.backend.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role fromRoleDTO(RoleDTO roleDTO);
    RoleDTO fromRoleToRoleDTO(Role role);
}
