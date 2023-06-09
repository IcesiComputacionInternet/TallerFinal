package co.icesi.automoviles.api;

import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.dto.PageResponse;
import co.icesi.automoviles.dto.PurchaseOrderCreateDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/purchaseOrders")
public interface PurchaseOrderAPI {
    public static final String ROOT_PATH = "/purchaseOrders";

    @PostMapping
    public PurchaseOrderShowDTO createPurchaseOrder(@Valid @RequestBody PurchaseOrderCreateDTO purchaseOrderCreateDTO);

    @GetMapping("{purchaseOrderId}")
    public PurchaseOrderShowDTO getPurchaseOrderById(@PathVariable("purchaseOrderId") String purchaseOrderId);

    @GetMapping
    public ResponseEntity<PageResponse<PurchaseOrderShowDTO>> getAllPurchaseOrder();

    @DeleteMapping("{purchaseOrderId}")
    public void deletePurchaseOrder(@PathVariable("purchaseOrderId") String purchaseOrderId);

    @PatchMapping("{purchaseOrderId}/{newState}")
    public PurchaseOrderShowDTO updateStateOfPurchaseOrder(@PathVariable("purchaseOrderId") String purchaseOrderId, @PathVariable("newState") String newState);

    @PatchMapping("{purchaseOrderId}")
    public PurchaseOrderShowDTO updatePurchaseOrder(@PathVariable("purchaseOrderId") String purchaseOrderId, @Valid @RequestBody PurchaseOrderCreateDTO purchaseOrderCreateDTO);
}
