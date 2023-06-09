package com.example.eshopbackend.controller;

import com.example.eshopbackend.api.OrderAPI;
import com.example.eshopbackend.dto.OrderDTO;
import com.example.eshopbackend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController implements OrderAPI {

    private final OrderService orderService;

    @Override
    public List<OrderDTO> getAll() {
        return orderService.getAllOrders();
    }

    @Override
    public void createOrder(OrderDTO orderDTO) {
        orderService.save(orderDTO);
    }
}
