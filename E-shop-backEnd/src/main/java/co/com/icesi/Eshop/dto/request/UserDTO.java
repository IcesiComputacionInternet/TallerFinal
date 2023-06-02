package co.com.icesi.Eshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String phoneNumber;


    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    private LocalDateTime birthDate;
}
