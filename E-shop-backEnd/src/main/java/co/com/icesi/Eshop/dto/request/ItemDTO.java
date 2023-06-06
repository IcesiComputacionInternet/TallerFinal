package co.com.icesi.Eshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    @NotBlank
    @NotNull
    private String description;
    @NotBlank
    @NotNull
    private String name;

    @Min(value = 0)
    @NotNull
    private Long price;
    @NotBlank
    @NotNull
    private String imageUrl;
    @NotBlank
    @NotNull
    private String category;

    private String orderId;

}
