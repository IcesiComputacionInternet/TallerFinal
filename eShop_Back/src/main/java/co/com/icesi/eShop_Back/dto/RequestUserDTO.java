package co.com.icesi.eShop_Back.dto;

import co.com.icesi.eShop_Back.validation.phone_number.interfaces.ColombianNumber;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
public record RequestUserDTO(

        String firstName,
        String lastName,
        String direction,
        LocalDateTime brithDay,
        @Email
        @NotNull
        String email,
        @NotBlank
        String password,
        @NotBlank
        @ColombianNumber
        String phoneNumber
) {


}
