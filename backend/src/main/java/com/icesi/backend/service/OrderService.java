package com.icesi.backend.service;

import com.icesi.backend.DTO.OrderCreateDTO;
import com.icesi.backend.DTO.OrderUpdateDTO;
import com.icesi.backend.mappers.ItemMapper;
import com.icesi.backend.mappers.OrderMapper;
import com.icesi.backend.models.Order;
import com.icesi.backend.respositories.ItemRepository;
import com.icesi.backend.respositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    public Optional<Order> createOrder(OrderCreateDTO orderCreateDTO){
        return Optional.of(orderRepository.save(orderMapper.fromOrderCreateDTO(orderCreateDTO)));
    }

    public Optional<Order> updateOrder(OrderUpdateDTO orderUpdateDTO){
        return Optional.of(orderRepository.save(orderMapper.fromOrderUpdateDTO(orderUpdateDTO)));
    }
}
