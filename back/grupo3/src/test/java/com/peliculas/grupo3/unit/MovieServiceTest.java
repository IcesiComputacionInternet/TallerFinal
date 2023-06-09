package com.peliculas.grupo3.unit;


import com.peliculas.grupo3.dto.CategoryDTO;
import com.peliculas.grupo3.dto.MovieDTO;
import com.peliculas.grupo3.mapper.MovieMapper;
import com.peliculas.grupo3.mapper.MovieMapperImpl;
import com.peliculas.grupo3.model.Category;
import com.peliculas.grupo3.model.Movie;
import com.peliculas.grupo3.repository.CategoryRepository;
import com.peliculas.grupo3.repository.MovieRepository;
import com.peliculas.grupo3.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MovieServiceTest {

    private MovieMapper movieMapper;

    private MovieRepository movieRepository;

    private MovieService movieService;

    private CategoryRepository categoryRepository;

    private final Category accion = Category.builder()
            .categoryId(UUID.randomUUID())
            .name("Accion")
            .description("Peliculas de accion")
            .build();

    @BeforeEach
    public void init(){
        movieRepository = mock(MovieRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        movieMapper = spy(MovieMapperImpl.class);
        movieService = new MovieService(movieRepository,movieMapper,categoryRepository);
    }

    @Test
    public void testCreateMovie(){
        when(categoryRepository.findByName("Accion")).thenReturn(Optional.ofNullable(accion));
        movieService.save(defaultMovieDTO());
        Movie movie = defaultMovie();
        verify(movieRepository,times(1)).findByName(argThat(name->name.equals(movie.getName())));
    }

    private MovieDTO defaultMovieDTO(){
        return MovieDTO.builder()
                .pgRating("PG-13")
                .imageURL("https://image.tmdb.org/t/p/original/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg")
                .price(10L)
                .description("After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse.")
                .name("Spider-Man: Migel Ohara is not definitly not racist")
                .categoryDTO(new CategoryDTO("Accion","Peliculas de accion"))
                .build();
    }



    private Movie defaultMovie(){
        return Movie.builder()
                .pgRating("PG-13")
                .imageURL("https://image.tmdb.org/t/p/original/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg")
                .price(10L)
                .description("After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse.")
                .name("Spider-Man: Migel Ohara is not definitly not racist")
                .category(accion)
                .build();
    }
}
