package com.peliculas.grupo3.service;

import com.peliculas.grupo3.dto.OrderDTO;
import com.peliculas.grupo3.dto.OrderTargetDTO;
import com.peliculas.grupo3.mapper.MovieMapper;
import com.peliculas.grupo3.mapper.OrderMapper;
import com.peliculas.grupo3.model.Movie;
import com.peliculas.grupo3.model.MovieOrder;
import com.peliculas.grupo3.model.MovieUser;
import com.peliculas.grupo3.repository.MovieRepository;
import com.peliculas.grupo3.repository.OrderRepository;
import com.peliculas.grupo3.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;



@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final UserRepository userRepository;

    private final MovieMapper movieMapper;

    private final MovieRepository movieRepository;

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


        String status = orderDTO.getStatus().toLowerCase();

        if(status.equals("cancelada") || status.equals("en proceso") || status.equals("confirmada") || status.equals("terminada")){
            orderDTO.setStatus(status);
        }else{
            throw new RuntimeException("El estado de la orden no es valido");
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

    public List<OrderDTO> findAll() {

        if(orderRepository.findAll().isEmpty()){
            throw new RuntimeException("No hay ordenes");
        }

        return orderRepository.findAll().stream().map(orderMapper::fromOrder).toList();
    }

    public OrderDTO findByOrderNumber(String orderNumber) {
        String decodedName;
        try {
            decodedName = URLDecoder.decode(orderNumber, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema al transoformar");
        }
        return orderMapper.fromOrder(orderRepository.findByOrderNumber(decodedName).orElseThrow(
                ()-> new RuntimeException("No existe una orden con este numero") ));
    }

    public OrderDTO addMovie(OrderTargetDTO targetDTO){
        MovieOrder order = orderRepository.findByOrderNumber(targetDTO.getOrdernumber()).orElseThrow(
                ()-> new RuntimeException("No existe una orden con este numero") );

        Movie movie = movieRepository.findByName(targetDTO.getMovieName()).orElseThrow(
                ()-> new RuntimeException("No existe una pelicula con este nombre") );

        order.getMovies().add(movie);
        order.setTotal(order.getTotal()+movie.getPrice());

        return orderMapper.fromOrder(order);
    }

    public List<OrderDTO> findByUser(String email) {
        String decodedName;
        try {
            decodedName = URLDecoder.decode(email, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema al transoformar");
        }
        MovieUser user = userRepository.findByEmail(decodedName).orElseThrow(
                ()-> new RuntimeException("No existe un usuario con este email") );

        if(user.getMovieOrders().isEmpty()){
            throw new RuntimeException("El usuario no tiene ordenes");
        }

        return user.getMovieOrders().stream().map(orderMapper::fromOrder).toList();
    }

    public OrderDTO changeStatus(String number, String status){
        MovieOrder order = orderRepository.findByOrderNumber(number).orElseThrow(
                ()-> new RuntimeException("No existe una orden con este numero") );

        if(status.equalsIgnoreCase("cancelada")
                || status.equalsIgnoreCase("en proceso")
                || status.equalsIgnoreCase("confirmada")
                || status.equalsIgnoreCase("terminada")){
            order.setStatus(status);
        }else{
            throw new RuntimeException("El estado de la orden no es valido");
        }

        return orderMapper.fromOrder(order);
    }

    public OrderDTO removeMovie(OrderTargetDTO targetDTO){
        MovieOrder order = orderRepository.findByOrderNumber(targetDTO.getOrdernumber()).orElseThrow(
                ()-> new RuntimeException("No existe una orden con este numero") );

        Movie movie = movieRepository.findByName(targetDTO.getMovieName()).orElseThrow(
                ()-> new RuntimeException("No existe una pelicula con este nombre") );

        if(!order.getMovies().contains(movie)){
            throw new RuntimeException("La orden no contiene esta pelicula");
        }

        order.getMovies().remove(movie);
        order.setTotal(order.getTotal()-movie.getPrice());

        return orderMapper.fromOrder(order);
    }

}
