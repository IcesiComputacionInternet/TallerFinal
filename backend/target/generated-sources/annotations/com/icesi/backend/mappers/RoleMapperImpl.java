package com.icesi.backend.mappers;

import com.icesi.backend.DTO.RoleCreateDTO;
import com.icesi.backend.models.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-06-09T01:29:28-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
=======
    date = "2023-06-09T00:43:04-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
>>>>>>> 61551b68a70fbc0cf6de4a9cf5ae1f2fb468c755
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role fromRoleCreateDTO(RoleCreateDTO roleCreateDTO) {
        if ( roleCreateDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.roleName( roleCreateDTO.getRoleName() );
        role.description( roleCreateDTO.getDescription() );

        return role.build();
    }
}
