package co.icesi.automoviles.error.exception;

import lombok.Getter;

@Getter
public class ShopException extends RuntimeException {
    private final ShopError error;

    public ShopException(String message, ShopError error) {
        super(message);
        this.error = error;
    }
}
