package co.com.icesi.backend.error.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
public class CellphoneError {
    private HttpStatus status;
    private List<CellphoneErrorDetail> details;
}
