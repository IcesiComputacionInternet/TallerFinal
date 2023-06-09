package co.edu.icesi.Eshop.mapper;

import co.edu.icesi.Eshop.dto.UserDTO;
import co.edu.icesi.Eshop.model.EShopUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    EShopUser fromUserDTO(UserDTO userDTO);

    @Mapping(target = "roleName", expression = "java(eShopUser.getRole().getRoleName())")
    @Mapping(target="password",ignore = true)
    UserDTO fromUser(EShopUser eShopUser);
}
