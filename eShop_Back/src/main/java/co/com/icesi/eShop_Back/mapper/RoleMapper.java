package co.com.icesi.eShop_Back.mapper;

import co.com.icesi.eShop_Back.dto.request.RoleDTO;
import co.com.icesi.eShop_Back.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role fromRoleDTO(RoleDTO roleDTO);
    RoleDTO fromRole(Role role);
}
