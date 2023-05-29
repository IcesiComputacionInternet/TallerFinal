package co.com.icesi.eShopBackEnd.dto.response;

import co.com.icesi.eShopBackEnd.model.Role;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class ResponseUserDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String birthday;
    private Role role;
}
