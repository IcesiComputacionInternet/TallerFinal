package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.MovieDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.peliculas.grupo3.api.MovieApi.BASE_MOVIE_URL;

@RequestMapping(value = BASE_MOVIE_URL)
public interface MovieApi {
    String BASE_MOVIE_URL = "/movies";

    //TODO crear los metodos para el crud de peliculas

    @PostMapping("/")
    MovieDTO createMovie(@RequestBody MovieDTO movieDTO);

    @GetMapping("/all")
    List<MovieDTO> getAllMovies();

    @GetMapping("/{movie}")
    MovieDTO findByName(@PathVariable String movie);

    @GetMapping("/findByCategory/{category}")
    List<MovieDTO> findByCategory(@PathVariable String category);
}
