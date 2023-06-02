package co.com.icesi.eShopBackEnd.dto;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
public record CreateItemDTO(

        @NotBlank
        String name,

        @NotBlank
        @Min(value = 0, message = "El precio debe ser mayor a 0")
        Long price,

        @NotBlank
        String category,
        String imageURL,
        String description
) {
}
