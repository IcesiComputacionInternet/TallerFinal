package com.example.eshopbackend.mapper;

import com.example.eshopbackend.dto.RoleDTO;
import com.example.eshopbackend.dto.UserDTO;
import com.example.eshopbackend.model.Role;
import com.example.eshopbackend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromUserDTO(UserDTO role);
    UserDTO fromUser(User role);
}
