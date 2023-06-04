package co.com.icesi.backend.error.exception;

import lombok.Getter;

@Getter
public class CellphoneException extends RuntimeException{
    private final CellphoneError error;

    public CellphoneException(String message, CellphoneError error){
        super(message);
        this.error = error;
    }
}
