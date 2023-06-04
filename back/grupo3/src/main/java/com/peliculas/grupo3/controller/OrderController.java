package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.api.OrderApi;
import com.peliculas.grupo3.dto.OrderDTO;
import com.peliculas.grupo3.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        return orderService.findAll();
    }

    @Override
    public OrderDTO findByNumber(String number) {
        return orderService.findByOrderNumber(number);
    }

    @Override
    public OrderDTO addMovie(String number, String movieName) {
        return orderService.addMovie(number, movieName);
    }

    @Override
    public List<OrderDTO> getUserOrders(String email) {
        return orderService.findByUser(email);
    }

    //TODO implementar metodos de la api
}
