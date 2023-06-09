package com.example.eshopbackend.api;

import com.example.eshopbackend.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(OrderAPI.ORDER_BASE_URL)
public interface OrderAPI {
    String ORDER_BASE_URL = "/orders";

    @GetMapping("/getAll")
    List<OrderDTO> getAll();

    @PostMapping
    void createOrder(OrderDTO orderDTO);

    @PatchMapping("/update/{orderId}")
    void updateOrder(@PathVariable String orderId, String status);


}
