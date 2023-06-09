package co.icesi.automoviles.api;

import co.icesi.automoviles.dto.PageResponse;
import co.icesi.automoviles.dto.PurchaseOrderCreateDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/purchaseOrders")
public interface PurchaseOrderAPI {
    String ROOT_PATH = "/purchaseOrders";

    @PostMapping
    PurchaseOrderShowDTO createPurchaseOrder(@Valid @RequestBody PurchaseOrderCreateDTO purchaseOrderCreateDTO);

    @GetMapping("{purchaseOrderId}")
    PurchaseOrderShowDTO getPurchaseOrderById(@PathVariable("purchaseOrderId") String purchaseOrderId);

    @GetMapping
    ResponseEntity<PageResponse<PurchaseOrderShowDTO>> getAllPurchaseOrder(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "per_page", defaultValue = "5") Integer perPage,
            @RequestParam(name = "sort", defaultValue = "total") String sortBy,
            @RequestParam(name = "sort_dir", defaultValue = "asc") String sortDirection
    );

    @DeleteMapping("{purchaseOrderId}")
    void deletePurchaseOrder(@PathVariable("purchaseOrderId") String purchaseOrderId);

    @PatchMapping("{purchaseOrderId}/{newState}")
    PurchaseOrderShowDTO updateStateOfPurchaseOrder(@PathVariable("purchaseOrderId") String purchaseOrderId, @PathVariable("newState") String newState);

    @PatchMapping("{purchaseOrderId}")
    PurchaseOrderShowDTO updatePurchaseOrder(@PathVariable("purchaseOrderId") String purchaseOrderId, @Valid @RequestBody PurchaseOrderCreateDTO purchaseOrderCreateDTO);
}
