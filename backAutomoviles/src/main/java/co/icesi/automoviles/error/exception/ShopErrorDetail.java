package co.icesi.automoviles.error.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ShopErrorDetail {
    private String errorCode;
    private String errorMessage;
}