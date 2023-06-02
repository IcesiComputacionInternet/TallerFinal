package com.peliculas.grupo3.repository;

import com.peliculas.grupo3.model.MovieUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<MovieUser, UUID> {
    Optional<MovieUser> findByEmail(String fromString);

    Optional<MovieUser> findByPhone(String fromString);
}
