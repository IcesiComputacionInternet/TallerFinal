package co.com.icesi.eShop_Back.error.exception;


import co.com.icesi.eShop_Back.error.util.CustomError;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private CustomError error;
    public CustomException(CustomError error) {
        super();
        this.error = error;
    }

    public CustomException(String message) {
        super(message);
    }
}
