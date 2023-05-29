package co.com.icesi.eShopBackEnd.error.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ArgumentsErrorDetail {
    private String errorCode;
    private String errorMessage;
}
