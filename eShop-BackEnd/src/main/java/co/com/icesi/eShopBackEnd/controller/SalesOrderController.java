package co.com.icesi.eShopBackEnd.controller;

import co.com.icesi.eShopBackEnd.api.SalesOrderAPI;
import co.com.icesi.eShopBackEnd.dto.CreateSalesOderDTO;
import co.com.icesi.eShopBackEnd.dto.UpdateOrderStateDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseSalesOrderDTO;
import co.com.icesi.eShopBackEnd.service.SalesOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SalesOrderController implements SalesOrderAPI {

    private final SalesOrderService salesOrderService;
    @Override
    public ResponseSalesOrderDTO createSalesOrder(CreateSalesOderDTO salesOrder) {
        return salesOrderService.save(salesOrder);
    }

    @Override
    public ResponseSalesOrderDTO updateState(UpdateOrderStateDTO salesOrder) {
        return salesOrderService.updateOrderState(salesOrder);
    }
}
