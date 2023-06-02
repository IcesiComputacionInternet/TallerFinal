package co.com.icesi.eShopBackEnd.dto;


import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record CreateCategoryDTO(

        @NotBlank
        String name,
        String description
) {

}
