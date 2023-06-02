package com.peliculas.grupo3.error;
import com.peliculas.grupo3.error.util.MovieExceptionBuilder;
import com.peliculas.grupo3.error.exception.MovieError;
import com.peliculas.grupo3.error.exception.MovieException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieException.class)
    public ResponseEntity<MovieError> handleIcesiException(MovieException icesiException){
        return ResponseEntity.status(icesiException.getError().getStatus()).body(icesiException.getError());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MovieError> handleRuntimeException(RuntimeException runtimeException){
        var error = MovieExceptionBuilder.createMovieError(HttpStatus.INTERNAL_SERVER_ERROR,runtimeException.getMessage());
        System.out.println(runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
