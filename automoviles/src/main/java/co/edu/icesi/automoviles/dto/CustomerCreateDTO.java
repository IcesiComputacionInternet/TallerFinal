package co.edu.icesi.automoviles.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreateDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime birthDate;
    private String password;  
    private RoleShowDTO role;


}
