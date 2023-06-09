package co.com.icesi.backend.mapper;

import co.com.icesi.backend.dto.request.RequestUserDTO;
import co.com.icesi.backend.dto.response.ResponseUserDTO;
import co.com.icesi.backend.model.ShopUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", source = "role",ignore=true)
    @Mapping(target = "password", source = "password",ignore=true)
    @Mapping(target = "birthday", source = "birthday",ignore=true)
    ShopUser fromUserDTO(RequestUserDTO requestUserDTO);
    @Mapping(target = "role", expression = "java(shopUser.getRole().getRoleName())")
    ResponseUserDTO fromUserToResponseUserDTO(ShopUser shopUser);
}
