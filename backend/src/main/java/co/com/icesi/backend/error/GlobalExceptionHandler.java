package co.com.icesi.backend.error;

import co.com.icesi.backend.Enum.ErrorCode;
import co.com.icesi.backend.error.exception.CellphoneError;
import co.com.icesi.backend.error.exception.CellphoneErrorDetail;
import co.com.icesi.backend.error.exception.CellphoneException;
import co.com.icesi.backend.error.exception.DetailBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder.createCellphoneError;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CellphoneError> handleLoginException(
            BadCredentialsException badCredentialsException){
        var error = createCellphoneError(HttpStatus.UNAUTHORIZED, new DetailBuilder(ErrorCode.ERROR_401));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(CellphoneException.class)
    public ResponseEntity<CellphoneError> handleCellphoneException(CellphoneException cellphoneException){
        return ResponseEntity.status(cellphoneException.getError().getStatus()).body(cellphoneException.getError());
    }

   @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CellphoneError> handleRuntimeException(){
        var error = createCellphoneError(HttpStatus.INTERNAL_SERVER_ERROR, new DetailBuilder(ErrorCode.ERROR_500));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CellphoneError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){
        var errorBuilder = CellphoneError.builder().status(HttpStatus.BAD_REQUEST);
        var details = exception.getBindingResult().getAllErrors().stream().map(this::mapBindingResultError).toList();
        var error = errorBuilder.details(details).build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    private CellphoneErrorDetail mapBindingResultError(ObjectError objectError) {
        var message = ErrorCode.ERROR_400.getMessage().formatted(((FieldError) objectError).getField(), objectError.getDefaultMessage());
        return CellphoneErrorDetail.builder()
                .errorCode(ErrorCode.ERROR_400.getCode())
                .errorMessage(message)
                .build();
    }
}
