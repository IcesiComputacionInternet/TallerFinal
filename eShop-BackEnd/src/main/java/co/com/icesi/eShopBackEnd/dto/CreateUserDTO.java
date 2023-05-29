package co.com.icesi.eShopBackEnd.dto;

import co.com.icesi.eShopBackEnd.validation.validateEmailorPhone.EmailOrPhoneConstraint;
import co.com.icesi.eShopBackEnd.validation.validatePhoneNumber.ColombianNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Builder
@EmailOrPhoneConstraint
public record CreateUserDTO(

        String firstName,
        String lastName,
        String address,
        String birthday,
        @Email
        String email,
        @ColombianNumberConstraint
        String phoneNumber,
        @NotNull
        @NotBlank
        String password) {
}
