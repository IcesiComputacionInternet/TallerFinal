package co.com.icesi.eShop_Back.dto.request;

import co.com.icesi.eShop_Back.validation.phone_number.ColombianNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDTO{
        private String firstName;
        private String lastName;
        private String direction;
        private LocalDateTime brithDay;
        @Email
        @NotNull
        private String email;
        @NotBlank
        private String password;
        @NotBlank
        @ColombianNumber
        private String phoneNumber;
        @NotBlank
        private String role;

}
