package com.peliculas.grupo3.service;

import com.peliculas.grupo3.dto.MovieDTO;
import com.peliculas.grupo3.mapper.MovieMapper;
import com.peliculas.grupo3.model.Category;
import com.peliculas.grupo3.model.Movie;
import com.peliculas.grupo3.repository.CategoryRepository;
import com.peliculas.grupo3.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    private final CategoryRepository categoryRepository;

    public MovieDTO save(MovieDTO movieDTO){
        if(movieRepository.findByName(movieDTO.getName()).isPresent()){
            throw new RuntimeException("La pelicula ya existe");
        }

        Category category = categoryRepository.findByName(movieDTO.getCategoryName()).orElseThrow(
                ()-> new RuntimeException("La categoria no existe")
        );

        String pgRating= movieDTO.getPgRating();

        if(pgRating.equals("G") || pgRating.equals("PG") || pgRating.equals("PG-13") || pgRating.equals("R") || pgRating.equals("NC-17")){
            throw new RuntimeException("Category already exists");
        }

        if(movieDTO.getPrice()<=0){
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        Movie movie = movieMapper.fromMovieDTO(movieDTO);
        movie.setMovieId(UUID.randomUUID());
        movie.setCategory(category);
        movieRepository.save(movie);
        return movieDTO;
    }

    public List<MovieDTO> findAll(){
        return movieRepository.findAll().stream().map(movieMapper::fromMovie).toList();
    }

    public Optional<MovieDTO> findByName(String name){
        return movieRepository.findByName(name).map(movieMapper::fromMovie);
    }
}
