package com.example.eshopbackend.service;

import com.example.eshopbackend.mapper.OrderMapper;
import com.example.eshopbackend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

}
