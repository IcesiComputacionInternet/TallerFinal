package co.edu.icesi.automoviles.error.util;


import org.springframework.http.HttpStatus;

import co.edu.icesi.automoviles.error.exception.DetailBuilder;
import co.edu.icesi.automoviles.error.exception.ShopError;
import co.edu.icesi.automoviles.error.exception.ShopErrorDetail;
import co.edu.icesi.automoviles.error.exception.ShopException;

import java.util.Arrays;
import java.util.function.Supplier;

public class ShopExceptionBuilder {
    public static ShopError createIcesiError(String message, HttpStatus httpStatus, DetailBuilder... details){
        return ShopError.builder().status(httpStatus)
                .details(
                        Arrays.stream(details)
                                .map(ShopExceptionBuilder::mapToIcesiErrorDetail)
                                .toList()
                ).build();
    }

    public static ShopErrorDetail mapToIcesiErrorDetail(DetailBuilder detailBuilder) {
        return ShopErrorDetail.builder()
                .errorCode(detailBuilder.errorCode().getCode())
                .errorMessage(detailBuilder.errorCode().getMessage().formatted(detailBuilder.fields()))
                .build();
    }

    public static Supplier<ShopException> createIcesiException(String message, HttpStatus httpStatus, DetailBuilder... details) {
        return () -> new ShopException(message, createIcesiError(message, httpStatus, details));
    }

}
