package co.com.icesi.Eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
}
