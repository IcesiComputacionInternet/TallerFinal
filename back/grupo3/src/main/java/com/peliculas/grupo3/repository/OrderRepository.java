package com.peliculas.grupo3.repository;

import com.peliculas.grupo3.model.MovieOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<MovieOrder, UUID> {
}
