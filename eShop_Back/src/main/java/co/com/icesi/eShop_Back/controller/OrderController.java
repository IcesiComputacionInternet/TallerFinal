package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.controller.api.OrderApi;
import co.com.icesi.eShop_Back.dto.request.RequestOrderDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseOrderDTO;
import co.com.icesi.eShop_Back.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class OrderController implements OrderApi {
    private final OrderService orderService;
    @Override
    public void saveOrder(RequestOrderDTO orderDTO) {
        orderService.create(orderDTO);
    }

    @Override
    public ResponseOrderDTO getOrderById(String id) {
        return orderService.get(UUID.fromString(id));
    }

    @Override
    public void deleteOrderById(String id) {
        orderService.delete(UUID.fromString(id));
    }
}
