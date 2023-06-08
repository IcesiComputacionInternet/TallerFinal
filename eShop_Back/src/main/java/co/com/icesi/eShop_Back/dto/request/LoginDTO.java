package co.com.icesi.eShop_Back.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        String password
) {
}
