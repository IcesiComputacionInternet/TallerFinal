package co.edu.icesi.Eshop.dto;

import co.edu.icesi.Eshop.model.EShopUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @NotNull(message = "A name is required for the role")
    @Column(unique = true)
    private String roleName;

    private String description;

    private List<EShopUser> EShopUsers;
}
