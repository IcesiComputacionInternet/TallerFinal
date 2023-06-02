package co.com.icesi.eShopBackEnd.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record AssignCategoryDTO(

        @NotBlank
        String itemName,

        @NotBlank
        String category
) {
}
