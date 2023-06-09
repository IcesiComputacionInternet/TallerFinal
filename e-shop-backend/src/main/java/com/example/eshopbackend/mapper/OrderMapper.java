package com.example.eshopbackend.mapper;

import com.example.eshopbackend.dto.OrderDTO;
import com.example.eshopbackend.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order fromOrderDTO(OrderDTO orderDTO);
    OrderDTO fromOrder(Order order);
}
