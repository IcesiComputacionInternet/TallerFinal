package co.com.icesi.backend.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderItemDTO {
    private UUID id;
    @Min(value=1, message = "The quantity of an item must be greater than zero")
    private int quantity;
}
