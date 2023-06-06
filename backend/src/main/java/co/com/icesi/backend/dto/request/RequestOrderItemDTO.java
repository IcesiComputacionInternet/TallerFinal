package co.com.icesi.backend.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderItemDTO {
    private UUID orderId;
    @NotNull(message = "The item name of an order can't be null")
    @NotBlank(message = "The item name of an order can't be blank")
    private String item;
    private boolean deleteItem;
    @Min(value = 1, message = "The item's amount can't be below 1")
    private int amount;
    private String status;
}

