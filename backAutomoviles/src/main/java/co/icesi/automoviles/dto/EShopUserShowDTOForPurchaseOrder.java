package co.icesi.automoviles.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class EShopUserShowDTOForPurchaseOrder {
    private UUID eShopUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime birthDate;
}
