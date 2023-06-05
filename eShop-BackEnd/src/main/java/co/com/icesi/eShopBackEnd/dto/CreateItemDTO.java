package co.com.icesi.eShopBackEnd.dto;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
public record CreateItemDTO(

        @NotBlank
        String name,

        @Min(value = 0, message = "El precio debe ser mayor a 0")
        Long price,

        @NotBlank
        String category,

        @NotBlank
        String brand,

        @Min(value = 0, message = "El stock debe ser mayor a 0")
        int stock,
        String imageURL,
        String description
) {
}
