package co.com.icesi.eShop_Back.dto.response;

import co.com.icesi.eShop_Back.enums.Status;
import co.com.icesi.eShop_Back.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDTO {
    private UUID orderId;
    private UUID user;
    private Status status;
    private Long total;
    private List<ResponseItemDTO> items;
}
