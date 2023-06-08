package com.icesi.backend.mappers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.models.ShopUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ShopUser fromUserCreateDTO(UserCreateDTO userCreateDTO);
}
