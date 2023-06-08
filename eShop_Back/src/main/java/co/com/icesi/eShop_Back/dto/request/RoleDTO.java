package co.com.icesi.eShop_Back.dto.request;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record RoleDTO(
        @NotBlank
        String name,
        String description
) {}
