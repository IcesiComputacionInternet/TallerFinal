package co.edu.icesi.Eshop.dto;

import co.edu.icesi.Eshop.model.EShopUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @NotBlank(message = "A name is required for the role")
    private String roleName;

    @NotBlank(message = "is missing")
    private String description;

    private List<EShopUser> EShopUsers;
}
