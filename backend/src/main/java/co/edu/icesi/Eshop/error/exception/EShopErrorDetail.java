package co.edu.icesi.Eshop.error.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class EShopErrorDetail {

    private String errorCode;
    private String errorMessage;


}
