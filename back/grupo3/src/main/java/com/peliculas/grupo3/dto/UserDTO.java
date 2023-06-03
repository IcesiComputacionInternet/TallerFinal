package com.peliculas.grupo3.dto;

import com.peliculas.grupo3.validate.email.EmailConstraint;
import com.peliculas.grupo3.validate.phone.PhoneConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @EmailConstraint
    @NotBlank
    private String email;

    @PhoneConstraint
    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    @NotBlank
    private String roleName;

}
