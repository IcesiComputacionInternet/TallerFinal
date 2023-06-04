package co.icesi.automoviles.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleShowDTO {
    private UUID roleId;
    private String roleName;
    private String description;
    private List<CustomerShowDTO> customers;
}
