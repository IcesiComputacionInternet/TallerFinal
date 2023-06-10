package com.icesi.backend.error.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class EShopError {

    private HttpStatus status;
    private List<ErrorDetail> details;
}
