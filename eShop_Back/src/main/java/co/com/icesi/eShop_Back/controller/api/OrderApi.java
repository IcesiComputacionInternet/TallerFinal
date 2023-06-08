package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.request.RequestOrderDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseOrderDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(OrderApi.ORDER_BASE_URL)
public interface OrderApi {
    String ORDER_BASE_URL = "/api/v1/orders";

    @PostMapping
    void saveOrder(@RequestBody @Valid RequestOrderDTO orderDTO);

    @GetMapping("/get/id/{id}")
    ResponseOrderDTO getOrderById(@PathVariable String id);

    @DeleteMapping("/delete/id/{id}")
    void deleteOrderById(@PathVariable String id);
}
