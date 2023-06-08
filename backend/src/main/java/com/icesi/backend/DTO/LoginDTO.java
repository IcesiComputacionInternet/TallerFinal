package com.icesi.backend.DTO;

import icesi.VirtualStore.validation.CustomAnnotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotNull(message = "The username cannot be null")
    @ValidUsername
    private String username;

    @NotNull(message = "The password cannot be null")
    @ValidPassword
    private String password;

}
