package co.com.icesi.eShopBackEnd.dto.response;

import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ResponseCustomerDTO {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDate birthday;
    private CreateRoleDTO role;
}
