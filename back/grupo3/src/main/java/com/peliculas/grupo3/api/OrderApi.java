package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.OrderDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.peliculas.grupo3.api.OrderApi.BASE_ORDER_URL;

@RequestMapping(value = BASE_ORDER_URL)
public interface OrderApi {
    String BASE_ORDER_URL = "/orders";

    //TODO crear los metodos para el crud de ordenes

    @PostMapping("/")
    OrderDTO createOrder(@RequestBody OrderDTO orderDTO);

    @GetMapping("/all")
    List<OrderDTO> getAllOrder();

    @GetMapping("/findByNumber")
    OrderDTO findByNumber(@RequestBody String number);

    @PostMapping("/addMovie")
    OrderDTO addMovie(@RequestBody String number , String movieName);

    @PostMapping("/deleteMovie")
    OrderDTO deleteMovie(@RequestBody String number , String movieName);

    @GetMapping ("/getUserOrders")
    List<OrderDTO> getUserOrders(@RequestBody String email);

    @PostMapping("/changeStatus")
    OrderDTO changeStatus(@RequestBody String number, String status);

}
