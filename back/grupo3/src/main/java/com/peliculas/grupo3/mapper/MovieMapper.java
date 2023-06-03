package com.peliculas.grupo3.mapper;

import com.peliculas.grupo3.dto.MovieDTO;
import com.peliculas.grupo3.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie fromMovieDTO(MovieDTO movieDTO);

}
