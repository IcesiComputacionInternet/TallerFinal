package com.icesi.backend.mappers;

import com.icesi.backend.DTO.PermissionUserDTO;
import com.icesi.backend.DTO.RoleCreateDTO;
import com.icesi.backend.DTO.RoleDTO;
import com.icesi.backend.models.PermissionUser;
import com.icesi.backend.models.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-10T13:25:02-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230511-1142, environment: Java 17.0.7 (Eclipse Adoptium)"
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

    @Override
    public Role fromDTO(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.description( roleDTO.getDescription() );
        role.rolePermissions( permissionUserDTOListToPermissionUserList( roleDTO.getRolePermissions() ) );

        return role.build();
    }

    @Override
    public RoleDTO fromRole(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setDescription( role.getDescription() );
        roleDTO.setRolePermissions( permissionUserListToPermissionUserDTOList( role.getRolePermissions() ) );

        return roleDTO;
    }

    protected PermissionUser permissionUserDTOToPermissionUser(PermissionUserDTO permissionUserDTO) {
        if ( permissionUserDTO == null ) {
            return null;
        }

        PermissionUser.PermissionUserBuilder permissionUser = PermissionUser.builder();

        permissionUser.method( permissionUserDTO.getMethod() );
        permissionUser.permissionKey( permissionUserDTO.getPermissionKey() );
        permissionUser.uri( permissionUserDTO.getUri() );

        return permissionUser.build();
    }

    protected List<PermissionUser> permissionUserDTOListToPermissionUserList(List<PermissionUserDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<PermissionUser> list1 = new ArrayList<PermissionUser>( list.size() );
        for ( PermissionUserDTO permissionUserDTO : list ) {
            list1.add( permissionUserDTOToPermissionUser( permissionUserDTO ) );
        }

        return list1;
    }

    protected PermissionUserDTO permissionUserToPermissionUserDTO(PermissionUser permissionUser) {
        if ( permissionUser == null ) {
            return null;
        }

        PermissionUserDTO permissionUserDTO = new PermissionUserDTO();

        permissionUserDTO.setMethod( permissionUser.getMethod() );
        permissionUserDTO.setPermissionKey( permissionUser.getPermissionKey() );
        permissionUserDTO.setUri( permissionUser.getUri() );

        return permissionUserDTO;
    }

    protected List<PermissionUserDTO> permissionUserListToPermissionUserDTOList(List<PermissionUser> list) {
        if ( list == null ) {
            return null;
        }

        List<PermissionUserDTO> list1 = new ArrayList<PermissionUserDTO>( list.size() );
        for ( PermissionUser permissionUser : list ) {
            list1.add( permissionUserToPermissionUserDTO( permissionUser ) );
        }

        return list1;
    }
}
