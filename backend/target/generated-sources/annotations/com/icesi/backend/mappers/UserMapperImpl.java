package com.icesi.backend.mappers;

import com.icesi.backend.DTO.PermissionUserDTO;
import com.icesi.backend.DTO.RoleDTO;
import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.DTO.UserDTO;
import com.icesi.backend.DTO.UserUpdateDTO;
import com.icesi.backend.models.PermissionUser;
import com.icesi.backend.models.Role;
import com.icesi.backend.models.ShopUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-10T15:37:05-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ShopUser fromUserCreateDTO(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

        shopUser.password( userCreateDTO.getPassword() );
        shopUser.email( userCreateDTO.getEmail() );
        shopUser.phoneNumber( userCreateDTO.getPhoneNumber() );

        return shopUser.build();
    }

    @Override
    public ShopUser fromUserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        if ( userUpdateDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

        shopUser.userId( userUpdateDTO.getUserId() );
        shopUser.password( userUpdateDTO.getPassword() );
        shopUser.firstName( userUpdateDTO.getFirstName() );
        shopUser.lastName( userUpdateDTO.getLastName() );
        shopUser.email( userUpdateDTO.getEmail() );
        shopUser.phoneNumber( userUpdateDTO.getPhoneNumber() );
        shopUser.address( userUpdateDTO.getAddress() );
        shopUser.birthday( userUpdateDTO.getBirthday() );

        return shopUser.build();
    }

    @Override
    public ShopUser fromDTO(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

        shopUser.userId( userDTO.getUserId() );
        shopUser.email( userDTO.getEmail() );
        shopUser.phoneNumber( userDTO.getPhoneNumber() );
        shopUser.address( userDTO.getAddress() );
        shopUser.role( roleDTOToRole( userDTO.getRole() ) );

        return shopUser.build();
    }

    @Override
    public UserDTO fromUser(ShopUser user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId( user.getUserId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );
        userDTO.setAddress( user.getAddress() );
        userDTO.setRole( roleToRoleDTO( user.getRole() ) );

        return userDTO;
    }

    @Override
    public ShopUser fromDTO(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

        shopUser.password( userCreateDTO.getPassword() );
        shopUser.email( userCreateDTO.getEmail() );
        shopUser.phoneNumber( userCreateDTO.getPhoneNumber() );

        return shopUser.build();
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

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.description( roleDTO.getDescription() );
        role.rolePermissions( permissionUserDTOListToPermissionUserList( roleDTO.getRolePermissions() ) );

        return role.build();
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

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setDescription( role.getDescription() );
        roleDTO.setRolePermissions( permissionUserListToPermissionUserDTOList( role.getRolePermissions() ) );

        return roleDTO;
    }
}
