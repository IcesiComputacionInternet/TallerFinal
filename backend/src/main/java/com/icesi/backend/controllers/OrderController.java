package com.icesi.backend.controllers;

import com.icesi.backend.DTO.OrderDTO;
import com.icesi.backend.DTO.OrderUpdateDTO;
import com.icesi.backend.mappers.OrderMapper;
import com.icesi.backend.service.impl.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    public final OrderService orderService;

    public  final OrderMapper orderMapper;



    public void updateOrder(OrderUpdateDTO orderUpdateDTO) {
        orderService.updateOrder(orderUpdateDTO.getOrderId(),orderUpdateDTO.getStatus().toString());
    }


    public void deleteOrder(UUID orderId) {
        orderService.deleteOrder(orderId);
    }

}
