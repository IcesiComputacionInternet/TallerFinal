package co.edu.icesi.automoviles.error.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
public class ShopError {

    private HttpStatus status;
    private List<ShopErrorDetail> details;

}

