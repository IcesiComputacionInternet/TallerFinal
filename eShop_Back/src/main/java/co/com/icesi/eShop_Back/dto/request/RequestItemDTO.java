package co.com.icesi.eShop_Back.dto.request;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public record RequestItemDTO(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Description is mandatory")
        String description,
        @NotNull(message = "Price is mandatory")
        @Min(value = 0, message = "Price must be greater than 0")
        Long price,
        @NotBlank(message = "Image URL is mandatory")
        String imageUrl,
        @NotBlank(message = "Category is mandatory")
        String category
) {
}
