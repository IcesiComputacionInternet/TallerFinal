package co.com.icesi.eShop_Back.mapper;

import co.com.icesi.eShop_Back.dto.RequestUserDTO;
import co.com.icesi.eShop_Back.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserDTO(RequestUserDTO userDTO);
}
