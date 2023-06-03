package co.edu.icesi.automoviles.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseOrderShowDTO {
    private UUID purchaseOrderId;
    private CustomerShowDTO customer;
    private String status;
    private long total;
    private List<ItemShowDTO> items;
}
