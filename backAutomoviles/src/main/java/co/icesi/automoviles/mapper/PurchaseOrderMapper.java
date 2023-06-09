package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.PurchaseOrderCreateDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import co.icesi.automoviles.model.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    @Mapping(target = "items", ignore = true)
    PurchaseOrder fromPurchaseOrderCreateDTOToPurchaseOrder(PurchaseOrderCreateDTO purchaseOrderCreateDTO);
    @Mapping(target = "eShopUser", ignore = true)
    PurchaseOrderShowDTO fromPurchaseOrderToPurchaseOrderShowDTO(PurchaseOrder purchaseOrder);
}
