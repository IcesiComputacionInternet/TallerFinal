package co.icesi.automoviles.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseOrderCreateDTO {
    private EShopUserCreateDTO eShopUser;
    private String status;
    private long total;
    private List<ItemCreateDTO> items;
}
