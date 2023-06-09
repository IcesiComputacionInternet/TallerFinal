package co.icesi.automoviles.dto;

import java.util.List;

import co.icesi.automoviles.enums.PurchaseOrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseOrderCreateDTO {
    private String eShopUserUUID;
    private List<String> items;
}
