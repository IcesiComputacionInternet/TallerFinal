package co.edu.icesi.Eshop.dto;

import co.edu.icesi.Eshop.validation.constraint.CustomEmailConstraint;
import co.edu.icesi.Eshop.validation.constraint.EmailOrPhoneNumberExistConstraint;
import co.edu.icesi.Eshop.validation.constraint.PhoneNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EmailOrPhoneNumberExistConstraint
public class UserDTO {

    private String firstName;

    private String lastName;

    @CustomEmailConstraint
    private String email;

    @PhoneNumberConstraint
    private String phoneNumber;

    private String address;

    private String birthday;

    @NotBlank(message = "is missing")
    private String password;

    @NotBlank(message = "is missing")
    private String roleName;
}
