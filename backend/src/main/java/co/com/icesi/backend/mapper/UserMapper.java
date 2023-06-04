package co.com.icesi.backend.mapper;

import co.com.icesi.backend.dto.request.RequestUserDTO;
import co.com.icesi.backend.dto.response.ResponseUserDTO;
import co.com.icesi.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", source = "role",ignore=true)
    User fromUserDTO(RequestUserDTO requestUserDTO);
    @Mapping(target = "role", source = "role",ignore=true)
    ResponseUserDTO fromUserToResponseUserDTO(User User);
}
