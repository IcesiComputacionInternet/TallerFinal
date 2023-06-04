package co.edu.icesi.Eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "is missing")
    private String firstName;

    @NotBlank(message = "is missing")
    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private LocalDateTime birthday;

    @NotBlank(message = "is missing")
    private String password;

    @NotBlank(message = "is missing")
    private String roleName;
}
