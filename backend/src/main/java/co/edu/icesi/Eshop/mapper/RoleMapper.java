package co.edu.icesi.Eshop.mapper;

import co.edu.icesi.Eshop.dto.RoleDTO;
import co.edu.icesi.Eshop.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role fromRoleDTO(RoleDTO roleDTO);

    RoleDTO fromRole(Role role);
}
