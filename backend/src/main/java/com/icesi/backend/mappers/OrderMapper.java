package com.icesi.backend.mappers;

import com.icesi.backend.DTO.OrderCreateDTO;
import com.icesi.backend.DTO.OrderUpdateDTO;
import com.icesi.backend.models.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order fromOrderCreateDTO(OrderCreateDTO orderCreateDTO);

    Order fromOrderUpdateDTO(OrderUpdateDTO orderUpdateDTO);
}
