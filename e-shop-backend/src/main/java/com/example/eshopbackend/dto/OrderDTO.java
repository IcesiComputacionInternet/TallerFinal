package com.example.eshopbackend.dto;

import com.example.eshopbackend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String orderId;
    private String userId;
    private Long total;
    private OrderStatus orderStatus;
    @NotEmpty
    private List<ItemDTO> items;
}
