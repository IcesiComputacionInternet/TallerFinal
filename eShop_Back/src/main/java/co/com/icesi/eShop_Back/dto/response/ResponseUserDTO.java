package co.com.icesi.eShop_Back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {
    private UUID userId;
    private String firstName;
    private String email;
}
