package co.com.icesi.backend.dto.request;

import co.com.icesi.backend.constraint.PhoneEmailConstraint;
import co.com.icesi.backend.constraint.PhoneNumberConstraint;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PhoneEmailConstraint
public class RequestUserDTO {
    private String firstName;

    private String lastName;

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "The password of a user can't be blank")
    @NotNull(message = "The password of a user can't be null")
    private String password;

    @PhoneNumberConstraint
    private String phoneNumber;

    private String address;

    private String birthday;

    @NotNull(message = "The role of a user can't be null")
    @NotBlank(message = "The role of a user can't be blank")
    private String role;
}
