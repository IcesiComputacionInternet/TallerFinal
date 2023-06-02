package com.peliculas.grupo3.api;

import org.springframework.web.bind.annotation.RequestMapping;

import static com.peliculas.grupo3.api.MovieApi.BASE_MOVIE_URL;

@RequestMapping(value = BASE_MOVIE_URL)
public interface MovieApi {
    String BASE_MOVIE_URL = "/movies";

    //TODO crear los metodos para el crud de peliculas
}
