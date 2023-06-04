package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.OrderDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.peliculas.grupo3.api.OrderApi.BASE_ORDER_URL;

@RequestMapping(value = BASE_ORDER_URL)
public interface OrderApi {
    String BASE_ORDER_URL = "/orders";

    //TODO crear los metodos para el crud de ordenes

    @PostMapping("/")
    OrderDTO createOrder(@RequestBody OrderDTO orderDTO);
}
