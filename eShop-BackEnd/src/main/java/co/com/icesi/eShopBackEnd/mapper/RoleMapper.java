package co.com.icesi.eShopBackEnd.mapper;

import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role fromCreateRoleDTO(CreateRoleDTO roleCreateDTO);
    CreateRoleDTO fromRole(Role role);
}
