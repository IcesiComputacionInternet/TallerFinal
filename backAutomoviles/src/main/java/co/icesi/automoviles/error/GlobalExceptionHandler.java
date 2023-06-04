package co.icesi.automoviles.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.exception.ShopError;
import co.icesi.automoviles.error.exception.ShopErrorDetail;
import co.icesi.automoviles.error.exception.ShopException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ShopError> handleValidationException(
            MethodArgumentNotValidException argumentNotValidException) {
        var errorBuilder = ShopError.builder().status(HttpStatus.BAD_REQUEST);
        var details = argumentNotValidException.getBindingResult().getAllErrors().stream().map(this::mapBindingResultToError).toList();
        var error = errorBuilder.details(details).build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ShopError> handleBindException(
            BindException argumentNotValidException) {
        var errorBuilder = ShopError.builder().status(HttpStatus.BAD_REQUEST);
        var details = argumentNotValidException.getBindingResult().getAllErrors().stream().map(this::mapBindingResultToError).toList();
        var error = errorBuilder.details(details).build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    private ShopErrorDetail mapBindingResultToError(ObjectError objectError){
        var message = ErrorCode.ERR_400.getMessage().formatted(((FieldError) objectError).getField(), objectError.getDefaultMessage());
        return ShopErrorDetail.builder()
                .errorCode(ErrorCode.ERR_400.getCode())
                .errorMessage(message).build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ShopError> handleLoginException(
            BadCredentialsException badCredentialsException) {
        var errorBuilder = ShopError.builder().status(HttpStatus.UNAUTHORIZED);
        List<ShopErrorDetail> details = new ArrayList<>();
        details.add(new ShopErrorDetail("401", badCredentialsException.getMessage()));
        var error = errorBuilder.details(details).build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(ShopException.class)
    public ResponseEntity<ShopError> handleIcesiException(ShopException icesiException){
        var error = icesiException.getError();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
