package co.com.icesi.eShopBackEnd.mapper;

import co.com.icesi.eShopBackEnd.dto.CreateUserDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseUserDTO;
import co.com.icesi.eShopBackEnd.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromCreateUserDTO(CreateUserDTO userCreateDTO);
    CreateUserDTO fromUser(User user);

    ResponseUserDTO fromUserToResponseUserDTO(User user);
}
