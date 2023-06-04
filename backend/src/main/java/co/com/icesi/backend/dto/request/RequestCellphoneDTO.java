package co.com.icesi.backend.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCellphoneDTO {
    @NotBlank(message = "The description of a cellphone can't be blank")
    private String description;

    @NotNull(message = "The name of a cellphone can't be null")
    @NotBlank(message = "The name of a cellphone can't be blank")
    private String name;

    @Min(value=0, message = "The price of a new cellphone must be greater than 0")
    private long price;

    @NotBlank(message = "The image URL of a cellphone can't be blank")
    private String imageUrl;

    @NotNull(message = "The category of a user can't be null")
    @NotBlank(message = "The category of a user can't be blank")
    private String category;
}
