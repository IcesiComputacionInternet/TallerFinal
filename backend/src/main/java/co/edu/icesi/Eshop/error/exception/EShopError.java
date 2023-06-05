package co.edu.icesi.Eshop.error.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
public class EShopError {

    private HttpStatus status;
    private List<EShopErrorDetail> details;

}