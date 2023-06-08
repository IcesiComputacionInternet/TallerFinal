package com.icesi.backend.mappers;

import com.icesi.backend.DTO.RoleCreateDTO;
import com.icesi.backend.models.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-08T13:14:51-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230413-0857, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role fromRoleCreateDTO(RoleCreateDTO roleCreateDTO) {
        if ( roleCreateDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.description( roleCreateDTO.getDescription() );
        role.roleName( roleCreateDTO.getRoleName() );

        return role.build();
    }
}
