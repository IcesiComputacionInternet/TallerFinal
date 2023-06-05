package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.OrderAPI;
import co.edu.icesi.Eshop.dto.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.Eshop.api.OrderAPI.BASE_ORDER_URL;

@RestController
@RequestMapping(BASE_ORDER_URL)
@AllArgsConstructor
public class OrderController implements OrderAPI {
    @Override
    public OrderDTO getOrder(String orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return null;
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        return null;
    }
}
