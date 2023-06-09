package co.com.icesi.eShop_Back.dto.request;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record RequestCategoryDTO(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Description is mandatory")
        String description,
        String imageUrl
) {
}
