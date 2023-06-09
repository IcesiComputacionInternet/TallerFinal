package com.icesi.backend.mappers;


import com.icesi.backend.DTO.OrderDTO;
import com.icesi.backend.DTO.OrderItemDTO;
import com.icesi.backend.errorConstants.OrderStatus;
import com.icesi.backend.models.Order;
import com.icesi.backend.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {



    default String fromOrderStatus(OrderStatus status) {
        return status.getMessage();
    }

    default OrderStatus toOrderStatus(String status) {
        return OrderStatus.valueOf(status);
    }
}
