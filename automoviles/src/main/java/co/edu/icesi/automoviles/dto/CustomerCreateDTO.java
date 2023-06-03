package co.edu.icesi.automoviles.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreateDTO {
    private String email;
    private String phoneNumber;
    private String password;

}
