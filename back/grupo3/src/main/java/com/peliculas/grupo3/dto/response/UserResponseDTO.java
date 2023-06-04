package com.peliculas.grupo3.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;


    @NotBlank
    private String email;


    @NotBlank
    private String phone;

    @NotBlank
    private String roleName;
}
