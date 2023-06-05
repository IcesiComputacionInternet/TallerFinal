package co.edu.icesi.Eshop.error;

import co.edu.icesi.Eshop.error.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static co.edu.icesi.Eshop.error.util.EShopExceptionBuilder.createEShopError;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EShopException.class)
    public ResponseEntity<EShopError> handleEShopException(EShopException EShopException){
        return ResponseEntity.status(EShopException.getError().getStatus()).body(EShopException.getError());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<EShopError> handleRuntimeException(RuntimeException runtimeException){
        var error = createEShopError(runtimeException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new DetailBuilder(ErrorCode.ERR_500));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }




    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<EShopError> handleAuthenticationException(AuthenticationException authenticationException){
        var error = createEShopError(authenticationException.getMessage(), HttpStatus.UNAUTHORIZED, new DetailBuilder(ErrorCode.ERR_LOGIN,"Login unauthorized"));
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EShopError> handleValidationExceptions( MethodArgumentNotValidException ex) {
        var errorBuilder = EShopError.builder().status(HttpStatus.BAD_REQUEST);
        var details = ex.getBindingResult().getAllErrors().stream().map(this::mapBindingResultToError).toList();
        var error = errorBuilder.details(details).build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    private EShopErrorDetail mapBindingResultToError(ObjectError objectError){
        if (objectError instanceof FieldError) {
            var fieldError = (FieldError) objectError;
            var message = ErrorCode.ERR_400.getMessage().formatted(fieldError.getField(), fieldError.getDefaultMessage());
            return EShopErrorDetail.builder()
                    .errorCode(ErrorCode.ERR_400.getCode())
                    .errorMessage(message)
                    .build();
        }
        var message = ErrorCode.ERR_400.getMessage().formatted("",objectError.getDefaultMessage());
        return EShopErrorDetail.builder()
                .errorCode(ErrorCode.ERR_400.getCode())
                .errorMessage(message)
                .build();

    }

}