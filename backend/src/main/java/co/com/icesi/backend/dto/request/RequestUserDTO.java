package co.com.icesi.backend.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDTO {
    @NotBlank(message = "The first name of a user can't be blank")
    private String firstName;

    @NotBlank(message = "The lastname of a user can't be blank")
    private String lastName;

    @NotNull(message = "The email of a user can't be null")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "The password of a user can't be blank")
    @NotNull(message = "The password of a user can't be null")
    private String password;

    @NotNull(message = "The phone number of a user can't be null")
    //AGREGAR PHONE CONSTRAINT!!!
    private String phoneNumber;

    @NotBlank(message = "The address of a user can't be blank")
    private String address;

    @NotBlank(message = "The birthday of a user can't be blank")
    private String birthday;

    @NotNull(message = "The role of a user can't be null")
    @NotBlank(message = "The role of a user can't be blank")
    private String role;
}
