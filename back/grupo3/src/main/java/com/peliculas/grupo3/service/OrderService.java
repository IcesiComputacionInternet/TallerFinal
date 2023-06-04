package com.peliculas.grupo3.service;

import com.peliculas.grupo3.dto.OrderDTO;
import com.peliculas.grupo3.mapper.MovieMapper;
import com.peliculas.grupo3.mapper.OrderMapper;
import com.peliculas.grupo3.model.Movie;
import com.peliculas.grupo3.model.MovieOrder;
import com.peliculas.grupo3.model.MovieUser;
import com.peliculas.grupo3.repository.OrderRepository;
import com.peliculas.grupo3.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final UserRepository userRepository;

    private final MovieMapper movieMapper;

    public OrderDTO save(OrderDTO orderDTO){

        if(orderDTO.getTotal()<0){
            throw new RuntimeException("El total no puede ser negativo");
        }

        MovieUser user = userRepository.findByEmail(orderDTO.getUser().getEmail()).orElseThrow(
                ()-> new RuntimeException("No existe un usuario con este email") );

        if(orderDTO.getOrderNumber().isEmpty()){
            throw new RuntimeException("El numero de orden no puede estar vacio");
        }

        if(orderDTO.getMovies().isEmpty() && orderDTO.getTotal()>0){
            throw new RuntimeException("No se puede tener un total mayor a 0 sin peliculas");
        }

        orderRepository.findByOrderNumber(orderDTO.getOrderNumber()).ifPresent(
                (order)->{throw new RuntimeException("Ya existe una orden con este numero");});

        MovieOrder order = orderMapper.fromOrderDTO(orderDTO);

        if(orderDTO.getMovies().isEmpty()){
            order.setMovies(List.of());
        }else {
            order.setMovies(orderDTO.getMovies().stream().map(movieMapper::fromMovieDTO).toList());
        }

        order.setUser(user);
        order.setOrderId(UUID.randomUUID());
        orderRepository.save(order);
        return orderDTO;
    }


}
