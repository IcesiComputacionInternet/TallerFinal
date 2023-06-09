package com.example.eshopbackend.dto;


import com.example.eshopbackend.validation.interfaces.UserCreationConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@UserCreationConstraint
public class UserDTO {

    private String userId;
    private String firstName;
    private String lastName;

    @NotNull
    private String email;

    @NotBlank
    private String password;
    @NotNull
    private String phoneNumber;
    private String address;
    private LocalDateTime birthDate;
    private RoleDTO role;

}
