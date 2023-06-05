package co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCustomerInOrderDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
