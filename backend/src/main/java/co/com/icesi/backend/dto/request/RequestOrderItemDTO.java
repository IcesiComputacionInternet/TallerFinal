package co.com.icesi.backend.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderItemDTO {
    @NotNull(message = "The item name of an order can't be null")
    @NotBlank(message = "The item name of an order can't be blank")
    private String item;
    private boolean deleteItem;
}

