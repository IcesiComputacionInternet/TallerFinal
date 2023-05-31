package co.com.icesi.Eshop.dto.response;

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
public class RoleResponseDTO {
    @NotBlank
    @NotEmpty
    @NotNull
    private String roleName;
    @NotBlank
    @NotEmpty
    @NotNull
    private String description;
}
