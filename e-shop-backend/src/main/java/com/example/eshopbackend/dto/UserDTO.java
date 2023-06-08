package com.example.eshopbackend.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDateTime birthDate;
    private RoleDTO role;

}
