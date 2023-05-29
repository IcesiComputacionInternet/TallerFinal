package co.com.icesi.eShopBackEnd.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record CreateRoleDTO(

        @NotBlank
        String name,
        String description) {
}
