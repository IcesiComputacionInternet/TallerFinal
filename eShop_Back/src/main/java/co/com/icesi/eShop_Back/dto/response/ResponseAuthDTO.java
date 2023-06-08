package co.com.icesi.eShop_Back.dto.response;

import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAuthDTO {
    private String token;
    private ResponseUserDTO user;
}