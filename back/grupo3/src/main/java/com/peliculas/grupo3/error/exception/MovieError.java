package com.peliculas.grupo3.error.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovieError {

    private HttpStatus status;
    private String details;


}
