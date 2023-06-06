package co.edu.icesi.Eshop.error.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EShopErrorDetail {

    private String errorCode;
    private String errorMessage;


}
