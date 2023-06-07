package com.peliculas.grupo3.error.exception;

import lombok.Getter;



@Getter
public class MovieException extends RuntimeException {

    private final MovieError error;

    public MovieException(String message, MovieError error) {
        super(message);
        this.error = error;
    }



}
