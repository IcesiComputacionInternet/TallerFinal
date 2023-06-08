package co.com.icesi.eShop_Back.mapper;

import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseUserDTO;
import co.com.icesi.eShop_Back.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", source = "role",ignore=true)
    User fromUserDTO(RequestUserDTO userDTO);
    @Mapping(target = "role", source = "role",ignore=true)
    RequestUserDTO fromUser(User user);

    ResponseUserDTO fromUserToResponseUserDTO(User user);
}
