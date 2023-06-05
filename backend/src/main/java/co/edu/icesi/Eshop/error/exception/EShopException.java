package co.edu.icesi.Eshop.error.exception;

import lombok.Getter;

@Getter
public class EShopException extends RuntimeException {

    private final EShopError error;

    public EShopException(String message, EShopError error) {
        super(message);
        this.error = error;
    }


}
