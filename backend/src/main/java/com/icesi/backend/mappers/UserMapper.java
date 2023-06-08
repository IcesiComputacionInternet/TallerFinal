package com.icesi.backend.mappers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.DTO.UserUpdateDTO;
import com.icesi.backend.models.ShopUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "password", target = "password")
    ShopUser fromUserCreateDTO(UserCreateDTO userCreateDTO);

    ShopUser fromUserUpdateDTO(UserUpdateDTO userUpdateDTO);
}
