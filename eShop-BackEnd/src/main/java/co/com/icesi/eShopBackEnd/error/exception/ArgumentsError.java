package co.com.icesi.eShopBackEnd.error.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
public class ArgumentsError {
    private HttpStatus status;
    private List<ArgumentsErrorDetail> details;
}
