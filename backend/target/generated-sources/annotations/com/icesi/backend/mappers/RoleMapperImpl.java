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
    date = "2023-06-10T15:43:31-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
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

        permissionUser.uri( permissionUserDTO.getUri() );
        permissionUser.permissionKey( permissionUserDTO.getPermissionKey() );
        permissionUser.method( permissionUserDTO.getMethod() );

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

        permissionUserDTO.setUri( permissionUser.getUri() );
        permissionUserDTO.setPermissionKey( permissionUser.getPermissionKey() );
        permissionUserDTO.setMethod( permissionUser.getMethod() );

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
