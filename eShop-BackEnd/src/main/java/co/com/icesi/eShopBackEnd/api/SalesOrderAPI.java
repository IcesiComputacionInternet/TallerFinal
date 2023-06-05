package co.com.icesi.eShopBackEnd.api;

import co.com.icesi.eShopBackEnd.dto.CreateSalesOderDTO;
import co.com.icesi.eShopBackEnd.dto.UpdateOrderStateDTO;
import co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse.ResponseSalesOrderDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static co.com.icesi.eShopBackEnd.api.SalesOrderAPI.BASE_SALES_ORDER_URL;

@RequestMapping(value = BASE_SALES_ORDER_URL)
public interface SalesOrderAPI {

    String BASE_SALES_ORDER_URL = "/salesOrder";

    @PostMapping
    ResponseSalesOrderDTO createSalesOrder(@Valid @RequestBody CreateSalesOderDTO salesOrder);

    @GetMapping("/{salesOrderId}")
    ResponseSalesOrderDTO getSalesOrderById(@PathVariable String salesOrderId);

    @PatchMapping("/updateState")
    ResponseSalesOrderDTO updateState(@Valid @RequestBody UpdateOrderStateDTO salesOrder);

    @GetMapping("/all")
    List<ResponseSalesOrderDTO> getAllOrders();
}
