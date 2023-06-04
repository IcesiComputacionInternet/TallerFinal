package co.icesi.automoviles.error.util;


import org.springframework.http.HttpStatus;

import co.icesi.automoviles.error.exception.DetailBuilder;
import co.icesi.automoviles.error.exception.ShopError;
import co.icesi.automoviles.error.exception.ShopErrorDetail;
import co.icesi.automoviles.error.exception.ShopException;

import java.util.Arrays;
import java.util.function.Supplier;

public class ShopExceptionBuilder {
    public static ShopError createShopError(String message, HttpStatus httpStatus, DetailBuilder... details){
        return ShopError.builder().status(httpStatus)
                .details(
                        Arrays.stream(details)
                                .map(ShopExceptionBuilder::mapToShopErrorDetail)
                                .toList()
                ).build();
    }

    public static ShopErrorDetail mapToShopErrorDetail(DetailBuilder detailBuilder) {
        return ShopErrorDetail.builder()
                .errorCode(detailBuilder.errorCode().getCode())
                .errorMessage(detailBuilder.errorCode().getMessage().formatted(detailBuilder.fields()))
                .build();
    }

    public static Supplier<ShopException> createShopException(String message, HttpStatus httpStatus, DetailBuilder... details) {
        return () -> new ShopException(message, createShopError(message, httpStatus, details));
    }

}
