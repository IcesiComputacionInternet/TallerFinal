package co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse;

import co.com.icesi.eShopBackEnd.Enum.OrderState;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseSalesOrderDTO {
    private UUID orderId;
    private OrderState status;
    private Long total;
    private ResponseCustomerInOrderDTO customer;
    private List<ResponseItemInOrderDTO> items;
}
