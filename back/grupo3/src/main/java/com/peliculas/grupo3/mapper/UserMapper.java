package com.peliculas.grupo3.mapper;

import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.model.MovieUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MovieUser fromUserDTO(UserDTO userDTO);
}
