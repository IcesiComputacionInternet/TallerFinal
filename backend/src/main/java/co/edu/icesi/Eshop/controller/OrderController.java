package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.OrderAPI;
import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.Eshop.api.OrderAPI.BASE_ORDER_URL;

@RestController
@RequestMapping(BASE_ORDER_URL)
@AllArgsConstructor
public class OrderController implements OrderAPI {
    private OrderService orderService;
    @Override
    public OrderDTO getOrder(String orderId) {
        return orderService.getOrder(orderId);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }
}
