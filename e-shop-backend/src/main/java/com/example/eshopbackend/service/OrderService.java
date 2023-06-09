package com.example.eshopbackend.service;

import com.example.eshopbackend.dto.ItemDTO;
import com.example.eshopbackend.dto.OrderDTO;
import com.example.eshopbackend.enums.OrderStatus;
import com.example.eshopbackend.mapper.ItemMapper;
import com.example.eshopbackend.mapper.OrderMapper;
import com.example.eshopbackend.model.Item;
import com.example.eshopbackend.model.Order;
import com.example.eshopbackend.repository.ItemRepository;
import com.example.eshopbackend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public OrderDTO save(OrderDTO orderDTO) {
        //List<Item> items = checkItemExistence(orderDTO.getItems());
        List<Item> items = orderDTO.getItems().stream().map(itemMapper::fromItemDTO).collect(Collectors.toList());
        Order order = orderMapper.fromOrderDTO(orderDTO);
        order.setOrderId(UUID.randomUUID());
        order.setItems(items);
        if (items.size()>0){
            order.setTotal(getTotalCost(items));
        }else {
            order.setTotal(0L);
        }
        order.setStatus(OrderStatus.PROCESS);

        return orderMapper.fromOrder(orderRepository.save(order));
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::fromOrder).toList();
    }
    
    private OrderDTO getOrderById(UUID orderId) {
        return orderMapper.fromOrder(orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order not found")));
    }

    private long getTotalCost(List<Item> items){
        return items.stream().map(Item::getPrice).reduce(0L, Long::sum);
    }

    private List<Item> checkItemExistence(List<ItemDTO> items) {
        if (items == null) {
            throw new IllegalArgumentException("La lista de items no puede ser nula");
        }
        return items.stream()
                .map(item -> itemRepository.findByName(item.getName()).orElseThrow(
                        () -> new RuntimeException("Item not found"))).toList();
    }

}
