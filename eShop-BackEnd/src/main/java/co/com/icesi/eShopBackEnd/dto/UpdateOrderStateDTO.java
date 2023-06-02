package co.com.icesi.eShopBackEnd.dto;

import co.com.icesi.eShopBackEnd.Enum.OrderState;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Builder
public record UpdateOrderStateDTO(
        @NotBlank
        UUID orderId,
        @NotBlank
        OrderState status
) {
}
