package co.com.icesi.Eshop.mapper;

import co.com.icesi.Eshop.dto.request.UserDTO;
import co.com.icesi.Eshop.dto.response.UserResponseDTO;
import co.com.icesi.Eshop.model.UserPrincipal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", expression = "java(userRepository.findRoleByName(userDTO.getRole()))")
    UserPrincipal toUser(UserDTO userDTO);

    @Mapping(target = "role", expression = "java(userRepository.findRoleByName(userResponseDTO.getRole()))")
    UserPrincipal toUser(UserResponseDTO userResponseDTO);

    @Mapping(target = "role", expression = "java(userResponseDTO.getRole().getRoleName())")
    UserDTO toUserDTO(UserPrincipal userPrincipal);

    @Mapping(target = "role", expression = "java(userResponseDTO.getRole())")
    UserDTO toUserDTO(UserResponseDTO userResponseDTO);

    UserResponseDTO toUserResponseDTO(UserPrincipal userPrincipal);

    UserResponseDTO toUserResponseDTO(UserDTO userDTO);
}
