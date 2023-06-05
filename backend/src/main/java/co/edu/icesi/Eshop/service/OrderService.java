package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.ChangeStatusDTO;
import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.error.exception.DetailBuilder;
import co.edu.icesi.Eshop.error.exception.ErrorCode;
import co.edu.icesi.Eshop.mapper.OrderMapper;
import co.edu.icesi.Eshop.model.EShopOrder;
import co.edu.icesi.Eshop.model.EShopUser;
import co.edu.icesi.Eshop.model.Item;
import co.edu.icesi.Eshop.model.Status;
import co.edu.icesi.Eshop.repository.ItemRepository;
import co.edu.icesi.Eshop.repository.OrderRepository;
import co.edu.icesi.Eshop.repository.UserRepository;
import lombok.AllArgsConstructor;
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
        return  orderMapper.fromOrder(orderRepository.findById(UUID.fromString(orderId)).orElseThrow(
                createEShopException(
                        "Order not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "Order",orderId )
                )
        ));
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    public OrderDTO save(OrderDTO orderDTO) {
        EShopUser EShopUser =null;
        if(orderDTO.getUserEmail()!=null){
             EShopUser = userRepository.findByEmail(orderDTO.getUserEmail()).orElseThrow(createEShopException(
                    "User does not exists",
                    HttpStatus.NOT_FOUND,
                    new DetailBuilder(ErrorCode.ERR_404, "User with email",orderDTO.getUserEmail() )
            ));
        }else if(orderDTO.getUserPhoneNumber()!=null){
            EShopUser = userRepository.findByPhoneNumber(orderDTO.getUserPhoneNumber()).orElseThrow(createEShopException(
                    "User does not exists",
                    HttpStatus.NOT_FOUND,
                    new DetailBuilder(ErrorCode.ERR_404, "User with phone number",orderDTO.getUserPhoneNumber() )
            ));
        }
        List<Item> items= orderDTO.getItems().stream().map(x-> itemRepository.findByName(x).get()).toList();
        EShopOrder order= orderMapper.fromOrderDTO(orderDTO);
        order.setOrderId(UUID.randomUUID());
        order.setEShopUser(EShopUser);
        order.setStatus(Status.PENDING.toString());
        order.setItems(items);

        return orderMapper.fromOrder(orderRepository.save(order));
    }


    public OrderDTO changeStatus(ChangeStatusDTO changeStatusDTO) {
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
