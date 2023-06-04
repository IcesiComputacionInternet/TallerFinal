package co.edu.icesi.Eshop.mapper;

import co.edu.icesi.Eshop.dto.UserDTO;
import co.edu.icesi.Eshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromUserDTO(UserDTO userDTO);

    @Mapping(target = "roleName", expression = "java(user.getRole().getName())")
    UserDTO fromUser(User user);
}
