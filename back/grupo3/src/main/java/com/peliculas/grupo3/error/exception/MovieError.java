package com.peliculas.grupo3.error.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class MovieError {

    private HttpStatus status;
    private String details;


}
