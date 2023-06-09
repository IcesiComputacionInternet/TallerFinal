package co.com.icesi.eShopBackEnd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    private String token;
    private String role;
    private UUID userId;
}
