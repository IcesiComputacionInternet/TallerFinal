package co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItemInOrderDTO {
    private UUID itemId;
    private String name;
    private Long price;
    private String brand;
}
