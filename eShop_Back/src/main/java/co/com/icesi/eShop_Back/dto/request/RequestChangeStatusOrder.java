package co.com.icesi.eShop_Back.dto.request;

import co.com.icesi.eShop_Back.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RequestChangeStatusOrder(
        @NotBlank
        String orderId,
        @NotNull
        Status status
) {
}
