package co.com.icesi.eShop_Back.service;

import co.com.icesi.eShop_Back.dto.request.RequestOrderDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseOrderDTO;
import co.com.icesi.eShop_Back.enums.Status;
import co.com.icesi.eShop_Back.error.exception.CustomException;
import co.com.icesi.eShop_Back.mapper.ItemMapper;
import co.com.icesi.eShop_Back.mapper.OrderMapper;
import co.com.icesi.eShop_Back.model.Order;
import co.com.icesi.eShop_Back.repository.ItemRepository;
import co.com.icesi.eShop_Back.repository.OrderRepository;
import co.com.icesi.eShop_Back.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;

    public void create(RequestOrderDTO orderDTO){
        var user = userRepository.findById(UUID.fromString(orderDTO.getUser())).orElseThrow(() -> new CustomException("User not found"));
        var items = itemRepository.findAllById(orderDTO.getItems().stream().map(UUID::fromString).toList());
        var order = orderMapper.orderDTOToOrder(orderDTO);

        order.setOrderId(UUID.randomUUID());
        order.setUser(user);
        order.setItems(items);
        order.setStatus(Status.PENDING);

        orderRepository.save(order);
    }

    public void delete(UUID id){
        orderRepository.deleteById(id);
    }

    public void updateStatus(UUID id, Status status){
        var order = orderRepository.findById(id).orElseThrow(() -> new CustomException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

    public ResponseOrderDTO get(UUID id){
        var order = orderRepository.findById(id).orElseThrow(() -> new CustomException("Order not found"));
        return createResponseOrderDTO(order);
    }

    public List<ResponseOrderDTO> getAll(){
        var orders = orderRepository.findAll();
        return orders.stream().map(this::createResponseOrderDTO).toList();
    }

    public ResponseOrderDTO getByUser(String email){
        var order = orderRepository.findByUserEmail(email).orElseThrow(() -> new CustomException("Order not found"));
        return createResponseOrderDTO(order);
    }

    private ResponseOrderDTO createResponseOrderDTO(Order aux){
        var items = aux.getItems().stream().map(itemMapper::fromItem).toList();
        var response = orderMapper.orderToOrderDTO(aux);
        response.setItems(items);
        return response;
    }




}
