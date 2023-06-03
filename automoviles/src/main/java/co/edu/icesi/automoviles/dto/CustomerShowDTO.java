package co.edu.icesi.automoviles.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerShowDTO {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime birthDate;
    private RoleShowDTO role;
    private List<PurchaseOrderShowDTO> orders;
}
