package co.com.icesi.Eshop.mapper;

import co.com.icesi.Eshop.dto.request.UserDTO;
import co.com.icesi.Eshop.dto.response.UserResponseDTO;
import co.com.icesi.Eshop.model.UserPrincipal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserPrincipal toUser(UserDTO userDTO);

    UserPrincipal toUser(UserResponseDTO userResponseDTO);

    UserDTO toUserDTO(UserPrincipal userPrincipal);

    UserDTO toUserDTO(UserResponseDTO userResponseDTO);

    UserResponseDTO toUserResponseDTO(UserPrincipal userPrincipal);

    UserResponseDTO toUserResponseDTO(UserDTO userDTO);
}
