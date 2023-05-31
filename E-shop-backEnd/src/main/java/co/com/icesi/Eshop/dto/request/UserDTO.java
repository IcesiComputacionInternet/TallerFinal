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
    @NotBlank
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotBlank
    @NotEmpty
    @NotNull
    private String password;
    @NotEmpty
    @NotBlank
    @NotNull
    private String phoneNumber;

    @NotEmpty
    @NotBlank
    @NotNull
    private String firstName;
    @NotEmpty
    @NotBlank
    @NotNull
    private String lastName;
    @NotEmpty
    @NotBlank
    @NotNull
    private String address;
    @NotEmpty
    @NotBlank
    @NotNull
    private LocalDateTime birthDate;
}
