package co.edu.icesi.Eshop.error.util;

import co.edu.icesi.Eshop.error.exception.DetailBuilder;
import co.edu.icesi.Eshop.error.exception.EShopError;
import co.edu.icesi.Eshop.error.exception.EShopErrorDetail;
import co.edu.icesi.Eshop.error.exception.EShopException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.function.Supplier;

public class EShopExceptionBuilder {

    public static Supplier<EShopException> createEShopException(String message, HttpStatus httpStatus, DetailBuilder... details) {
        return () -> new EShopException(message, createEShopError(message, httpStatus, details));
    }

    public static EShopError createEShopError(String message, HttpStatus httpStatus, DetailBuilder... details){
        return EShopError.builder().status(httpStatus)
                .details(
                        Arrays.stream(details)
                                .map(EShopExceptionBuilder::mapToEShopErrorDetail)
                                .toList()
                ).build();
    }

    public static EShopErrorDetail mapToEShopErrorDetail(DetailBuilder detailBuilder) {
        return EShopErrorDetail.builder()
                .errorCode(detailBuilder.getErrorCode().getCode())
                .errorMessage(detailBuilder.getErrorCode().getMessage().formatted(detailBuilder.getFields()))
                .build();

    }

}