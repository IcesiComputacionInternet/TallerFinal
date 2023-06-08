package com.icesi.backend.error.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class E_SHOP_Exception extends RuntimeException{

    private HttpStatus status;
    private E_SHOP_Error error;

}
