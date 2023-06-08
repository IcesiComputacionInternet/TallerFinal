package com.icesi.backend.error.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class E_SHOP_Error {

    private E_SHOP_Error code;
    private String message;
}
