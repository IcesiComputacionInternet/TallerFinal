package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.api.MovieApi;
import com.peliculas.grupo3.dto.MovieDTO;
import com.peliculas.grupo3.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
public class MovieController implements MovieApi {

    private final MovieService movieService;

    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {
        return movieService.save(movieDTO);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieService.findAll();
    }

    @Override
    public Optional<MovieDTO> findByName(String name) {
        return movieService.findByName(name);
    }
    //TODO implementar metodos de la api




}
