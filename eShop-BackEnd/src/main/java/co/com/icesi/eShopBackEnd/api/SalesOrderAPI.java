package co.com.icesi.eShopBackEnd.api;

import co.com.icesi.eShopBackEnd.dto.CreateSalesOderDTO;
import co.com.icesi.eShopBackEnd.dto.UpdateOrderStateDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseSalesOrderDTO;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static co.com.icesi.eShopBackEnd.api.SalesOrderAPI.BASE_SALES_ORDER_URL;

@RequestMapping(value = BASE_SALES_ORDER_URL)
public interface SalesOrderAPI {

    String BASE_SALES_ORDER_URL = "/salesOrder";

    @PostMapping
    ResponseSalesOrderDTO createSalesOrder(@Valid @RequestBody CreateSalesOderDTO salesOrder);

    @PatchMapping("/updateState")
    ResponseSalesOrderDTO updateState(@Valid @RequestBody UpdateOrderStateDTO salesOrder);
}
