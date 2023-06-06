package com.peliculas.grupo3.repository;


import com.peliculas.grupo3.model.Category;
import com.peliculas.grupo3.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Optional<Movie> findByName(String name);


    List<Movie> findByCategory(Category category);
}
