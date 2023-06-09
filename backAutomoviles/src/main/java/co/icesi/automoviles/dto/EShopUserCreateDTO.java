package co.icesi.automoviles.dto;

import java.time.LocalDateTime;

import co.icesi.automoviles.validations.cellphonenumber.ColombianPhoneNumber;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EShopUserCreateDTO {
    private String firstName;
    private String lastName;
    private String email;
    @ColombianPhoneNumber
    private String phoneNumber;
    private String address;
    private LocalDateTime birthDate;
    private String password;
}
