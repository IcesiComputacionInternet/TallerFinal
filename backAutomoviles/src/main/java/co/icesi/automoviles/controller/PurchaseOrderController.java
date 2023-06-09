package co.icesi.automoviles.controller;

import co.icesi.automoviles.api.PurchaseOrderAPI;
import co.icesi.automoviles.dto.PageResponse;
import co.icesi.automoviles.dto.PurchaseOrderCreateDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import co.icesi.automoviles.security.ShopSecurityContext;
import co.icesi.automoviles.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PurchaseOrderController implements PurchaseOrderAPI {

    private final PurchaseOrderService purchaseOrderService;

    @Override
    public PurchaseOrderShowDTO createPurchaseOrder(PurchaseOrderCreateDTO purchaseOrderCreateDTO) {
        String currentUserId = ShopSecurityContext.getCurrentUserId();
        String currentRole = ShopSecurityContext.getCurrentUserRole();
        return purchaseOrderService.createPurchaseOrder(purchaseOrderCreateDTO, currentUserId, currentRole);
    }

    @Override
    public PurchaseOrderShowDTO getPurchaseOrderById(String purchaseOrderId) {
        String currentUserId = ShopSecurityContext.getCurrentUserId();
        String currentRole = ShopSecurityContext.getCurrentUserRole();
        return purchaseOrderService.getPurchaseOrderShowDTOById(purchaseOrderId, currentUserId, currentRole);
    }

    @Override
    public ResponseEntity<PageResponse<PurchaseOrderShowDTO>> getAllPurchaseOrder() {
        return null;
    }

    @Override
    public void deletePurchaseOrder(String purchaseOrderId) {
        String currentUserId = ShopSecurityContext.getCurrentUserId();
        String currentRole = ShopSecurityContext.getCurrentUserRole();
        purchaseOrderService.deletePurchaseOrder(purchaseOrderId, currentUserId, currentRole);
    }

    @Override
    public PurchaseOrderShowDTO updateStateOfPurchaseOrder(String purchaseOrderId, String newState) {
        return purchaseOrderService.updateStateOfPurchaseOrder(purchaseOrderId, newState);
    }

    @Override
    public PurchaseOrderShowDTO updatePurchaseOrder(String purchaseOrderId, PurchaseOrderCreateDTO purchaseOrderCreateDTO) {
        return null;
    }
}
