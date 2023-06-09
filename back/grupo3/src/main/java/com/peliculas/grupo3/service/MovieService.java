package com.peliculas.grupo3.service;

import com.peliculas.grupo3.dto.CategoryDTO;
import com.peliculas.grupo3.dto.MovieDTO;
import com.peliculas.grupo3.mapper.CategoryMapper;
import com.peliculas.grupo3.mapper.MovieMapper;
import com.peliculas.grupo3.model.Category;
import com.peliculas.grupo3.model.Movie;
import com.peliculas.grupo3.repository.CategoryRepository;
import com.peliculas.grupo3.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;


    public MovieDTO save(MovieDTO movieDTO){
        if(movieRepository.findByName(movieDTO.getName()).isPresent()){
            throw new RuntimeException("La pelicula ya existe");
        }

        Category category = categoryRepository.findByName(movieDTO.getCategoryDTO().getName()).orElseThrow(
                ()-> new RuntimeException("La categoria no existe")
        );

        String pgRating= movieDTO.getPgRating();
        boolean correctRating = pgRating.equals("G") || pgRating.equals("PG") || pgRating.equals("PG-13") || pgRating.equals("R") || pgRating.equals("NC-17");
        if(!correctRating){
            throw new RuntimeException("El rating no es valido");
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
        List<CategoryDTO> categories = movieRepository.findAll().stream().map(movie -> movie.getCategory()).map(categoryMapper::fromCategory).toList();
        List<MovieDTO> movies = movieRepository.findAll().stream().map(movieMapper::fromMovie).toList();
        for (int i = 0; i < categories.size(); i++) {
            movies.get(i).setCategoryDTO(categories.get(i));
        }

        return movies;
    }

    public MovieDTO findByName(String name) {


        String decodedName;
        try {
            decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema al transformar");
        }

        CategoryDTO category = movieRepository.findByName(decodedName).map(movie -> movie.getCategory()).map(categoryMapper::fromCategory).orElseThrow(
                () -> new RuntimeException("La pelicula no tiene categoría")
        );
        MovieDTO movie = movieRepository.findByName(decodedName).map(movieMapper::fromMovie).orElseThrow(
                () -> new RuntimeException("La pelicula no existe"));
        movie.setCategoryDTO(category);
        return movie;
    }

    public List<MovieDTO> findByCategory(String name){
        String decodedName;
        try {
            decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema al transoformar");
        }

        Category category = categoryRepository.findByName(decodedName).orElseThrow(
                ()-> new RuntimeException("La categoria no existe")
        );

        return movieRepository.findByCategory(category).stream().map(movieMapper::fromMovie).toList();
    }
}
