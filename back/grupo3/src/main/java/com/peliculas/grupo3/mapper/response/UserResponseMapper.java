package com.peliculas.grupo3.mapper.response;

import com.peliculas.grupo3.dto.response.UserResponseDTO;
import com.peliculas.grupo3.model.MovieUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponseDTO fromUser(MovieUser user);
}
