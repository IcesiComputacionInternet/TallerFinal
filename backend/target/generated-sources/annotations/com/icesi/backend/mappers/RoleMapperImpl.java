package com.icesi.backend.mappers;

import com.icesi.backend.DTO.RoleCreateDTO;
import com.icesi.backend.models.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-06-08T16:57:29-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
=======
    date = "2023-06-08T18:32:13-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230413-0857, environment: Java 17.0.7 (Eclipse Adoptium)"
>>>>>>> 32a316027a2b393a76c4c23c9b37039da120afe5
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
