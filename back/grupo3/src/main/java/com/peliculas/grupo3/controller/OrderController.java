package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.api.OrderApi;
import com.peliculas.grupo3.dto.OrderDTO;
import com.peliculas.grupo3.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }
    //TODO implementar metodos de la api
}
