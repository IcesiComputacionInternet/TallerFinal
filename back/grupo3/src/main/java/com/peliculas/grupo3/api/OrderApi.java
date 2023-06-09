package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.OrderDTO;
import com.peliculas.grupo3.dto.OrderTargetDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.peliculas.grupo3.api.OrderApi.BASE_ORDER_URL;

@RequestMapping(value = BASE_ORDER_URL)
public interface OrderApi {
    String BASE_ORDER_URL = "/orders";

    @PostMapping("/")
    OrderDTO createOrder(@RequestBody OrderDTO orderDTO);

    @GetMapping("/all")
    List<OrderDTO> getAllOrder();

    @GetMapping("/findByNumber/{number}")
    OrderDTO findByNumber(@PathVariable String number);

    @PostMapping("/addMovie")
    OrderDTO addMovie(@RequestBody OrderTargetDTO targetDTO);

    @PostMapping("/deleteMovie")
    OrderDTO deleteMovie(@RequestBody OrderTargetDTO targetDTO);

    @GetMapping ("/getUserOrders/{email}")
    List<OrderDTO> getUserOrders(@PathVariable String email);

    @PostMapping("/changeStatus")
    OrderDTO changeStatus(@RequestBody OrderTargetDTO targetDTO);

}
