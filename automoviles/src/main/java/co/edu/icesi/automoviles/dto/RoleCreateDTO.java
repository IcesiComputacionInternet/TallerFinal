package co.edu.icesi.automoviles.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleCreateDTO {
    private String roleName;
    private String description;
}
