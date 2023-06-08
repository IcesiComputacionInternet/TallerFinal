package com.example.eshopbackend.dto;


import com.example.eshopbackend.validation.interfaces.UserCreationConstraint;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@Data
@UserCreationConstraint
public class UserDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    @NotBlank
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDateTime birthDate;
    private RoleDTO role;

}
