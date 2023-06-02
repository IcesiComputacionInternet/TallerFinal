package com.peliculas.grupo3.error.util;

import com.peliculas.grupo3.error.exception.MovieError;
import com.peliculas.grupo3.error.exception.MovieException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class MovieExceptionBuilder {

    public static Supplier<MovieException> createMovieException(String message, String details) {
        return () -> new MovieException(message, createMovieError(HttpStatus.BAD_REQUEST, details));
    }

    public static MovieException createMovieException(String message, HttpStatus httpStatus,String details) {
        return new MovieException(message, createMovieError(httpStatus, details));
    }

    public static MovieError createMovieError(HttpStatus httpStatus, String details){
        return MovieError.builder()
                .status(httpStatus)
                .details(details)
                .build();

    }



}
