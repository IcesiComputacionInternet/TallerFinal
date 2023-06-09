package com.example.eshopbackend.error.exception;

import com.example.eshopbackend.error.CustomError;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private CustomError error;
    public CustomException(CustomError error) {
        super();
        this.error = error;
    }

    public CustomException(String message) {
        super(message);
    }
}
