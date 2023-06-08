package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.ChangeStatusDTO;
import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.error.exception.DetailBuilder;
import co.edu.icesi.Eshop.error.exception.ErrorCode;
import co.edu.icesi.Eshop.mapper.OrderMapper;
import co.edu.icesi.Eshop.model.*;
import co.edu.icesi.Eshop.repository.ItemRepository;
import co.edu.icesi.Eshop.repository.OrderRepository;
import co.edu.icesi.Eshop.repository.UserRepository;
import co.edu.icesi.Eshop.security.EShopSecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static co.edu.icesi.Eshop.error.util.EShopExceptionBuilder.createEShopException;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    private UserRepository userRepository;

    private ItemRepository itemRepository;


    public OrderDTO getOrder(String orderId) {
        EShopOrder order=orderRepository.findById(UUID.fromString(orderId)).orElseThrow(
                createEShopException(
                        "Order not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "Order",orderId )
                )
        );

        if(EShopSecurityContext.getCurrentUserRole().equals(Roles.USER.toString()) && !order.getEShopUser().getUserId().toString().equals(EShopSecurityContext.getCurrentUserId())) {
            createEShopException(
                    "Unauthorized: This is not user's order",
                    HttpStatus.FORBIDDEN,
                    new DetailBuilder(ErrorCode.ERR_403, "Unauthorized: This is not user's order")
            ).get();
        }
        return  orderMapper.fromOrder(order);
    }

    public List<OrderDTO> getAllOrders() {
        if(!EShopSecurityContext.getCurrentUserRole().equals(Roles.USER.toString())) {
            return orderRepository.findAll().stream()
                    .map(orderMapper::fromOrder)
                    .toList();
        }
        EShopUser user=userRepository.findById(UUID.fromString(EShopSecurityContext.getCurrentUserId())).get();
        return user.getOrders().stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    public OrderDTO save(OrderDTO orderDTO) {
        AdminAndUserAuthorizationOnly();
        List<Item> items= orderDTO.getItems().stream().map(x-> itemRepository.findByName(x.getName()).orElseThrow(
                createEShopException(
                        "Item does not exists",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "Item",x )
                )
        )).toList();
        EShopOrder order= orderMapper.fromOrderDTO(orderDTO);
        order.setOrderId(UUID.randomUUID());
        order.setEShopUser(getCurrentUser());
        order.setStatus(Status.PENDING.toString());
        order.setItems(items);
        order.setTotal(calculateTotal(items));

        return orderMapper.fromOrder(orderRepository.save(order));
    }

    private long calculateTotal(List<Item> items){
        return items.stream().mapToLong(Item::getPrice).sum();
    }

  public EShopUser getCurrentUser(){
        return userRepository.findById(UUID.fromString(EShopSecurityContext.getCurrentUserId())).get();
    }

    public void AdminAndShopAuthorizationOnly(){
        if(EShopSecurityContext.getCurrentUserRole().equals(Roles.USER.toString())){
            throw createEShopException(
                    "Unauthorized: Admin and Shop only",
                    HttpStatus.FORBIDDEN,
                    new DetailBuilder(ErrorCode.ERR_403, "Unauthorized: Admin and Shop only")
            ).get();
        }
    }

    public void AdminAndUserAuthorizationOnly(){
        if(EShopSecurityContext.getCurrentUserRole().equals(Roles.SHOP.toString())){
            throw createEShopException(
                    "Unauthorized: Admin and User only",
                    HttpStatus.FORBIDDEN,
                    new DetailBuilder(ErrorCode.ERR_403, "Unauthorized: Admin and User only")
            ).get();
        }
    }

    public OrderDTO changeStatus(ChangeStatusDTO changeStatusDTO) {
        AdminAndShopAuthorizationOnly();
        EShopOrder order= orderRepository.findById(UUID.fromString(changeStatusDTO.getOrderId())).orElseThrow(createEShopException(
                "Order does not exists",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "Order ",changeStatusDTO.getOrderId() )
        ));

        order.setStatus(changeStatusDTO.getStatus());

        orderRepository.save(order);
        return orderMapper.fromOrder(order);
    }
}
