package co.com.icesi.backend.error.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CellphoneErrorDetail {
    private String errorCode;
    private String errorMessage;
}
