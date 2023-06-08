package com.example.eshopbackend.dto;

import com.example.eshopbackend.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {
    private String orderId;
    private String userId;
    private OrderStatus orderStatus;
    private List<ItemDTO> items;
}
