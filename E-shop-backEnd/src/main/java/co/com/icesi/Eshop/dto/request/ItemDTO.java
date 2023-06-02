package co.com.icesi.Eshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotBlank
    @NotNull
    private Long price;
    @NotBlank
    @NotNull
    private String imageUrl;
    @NotBlank
    @NotNull
    private String categoryId;
    @NotBlank
    @NotNull
    private String orderId;

}
