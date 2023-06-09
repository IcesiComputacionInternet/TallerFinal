package co.com.icesi.backend.dto.response;

import co.com.icesi.backend.dto.request.RoleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseUserDTO {
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
