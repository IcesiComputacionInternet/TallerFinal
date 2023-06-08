package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.RoleCreateDTO;
import co.icesi.automoviles.dto.RoleShowDTO;
import co.icesi.automoviles.model.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-08T16:09:00-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role fromRoleCreateDTOToRole(RoleCreateDTO roleCreateDTO) {
        if ( roleCreateDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.roleName( roleCreateDTO.getRoleName() );
        role.description( roleCreateDTO.getDescription() );

        return role.build();
    }

    @Override
    public RoleShowDTO fromRoleToRoleShowDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleShowDTO.RoleShowDTOBuilder roleShowDTO = RoleShowDTO.builder();

        roleShowDTO.roleId( role.getRoleId() );
        roleShowDTO.roleName( role.getRoleName() );
        roleShowDTO.description( role.getDescription() );

        return roleShowDTO.build();
    }
}
