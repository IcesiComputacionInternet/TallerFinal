package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.api.MovieApi;
import com.peliculas.grupo3.dto.MovieDTO;
import com.peliculas.grupo3.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;


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
    public MovieDTO findByName(String movie) {

        System.out.println(movie);
        String decodedName;
        try {
            decodedName = URLDecoder.decode(movie, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        System.out.println(decodedName);
        return movieService.findByName(decodedName);
    }

    @Override
    public List<MovieDTO> findByCategory(String category) {
        return movieService.findByCategory(category);
    }
    //TODO implementar metodos de la api




}
