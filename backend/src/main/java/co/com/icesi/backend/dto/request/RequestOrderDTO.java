package co.com.icesi.backend.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {
    @NotNull(message = "The email of a user can't be null")
    @NotBlank(message = "The email of a user can't be blank")
    private String userEmail;

    @Min(value=0, message = "The total of a new order must be greater than 0")
    private long total;
    @NotNull(message = "The items of an order can't be null")
    private List<String> items;
}

