package co.com.icesi.backend.service;

import co.com.icesi.backend.Enum.OrderStatus;
import co.com.icesi.backend.dto.request.RequestOrderItemDTO;
import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.OrderMapper;
import co.com.icesi.backend.model.Cellphone;
import co.com.icesi.backend.model.Order;
import co.com.icesi.backend.model.User;
import co.com.icesi.backend.repository.CellphoneRepository;
import co.com.icesi.backend.repository.OrderRepository;
import co.com.icesi.backend.repository.UserRepository;
import co.com.icesi.backend.security.CellphoneSecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CellphoneRepository cellphoneRepository;
    private final OrderMapper orderMapper;
    private final CellphoneShopExceptionBuilder exceptionBuilder;

    public ResponseOrderDTO updateOrder(RequestOrderItemDTO requestOrderDTO) {
        checkPermissions();
        var userEmail = CellphoneSecurityContext.getCurrentUserEmail();
        User user =  userRepository.findByEmail(userEmail).orElseThrow(
                () -> exceptionBuilder.notFoundException(
                        "The user with the specified email does not exists.", userEmail));
        Cellphone cellphone = checkIfItemExists(requestOrderDTO.getItem());
        Optional<Order> order = orderRepository.findByStatus("CREATING", userEmail);
        if (order.isPresent() && requestOrderDTO.isDeleteItem()){
            return removeItemFromOrder(cellphone, order.get());
        }
        if (order.isPresent()){
            return addItemToOrder(cellphone, order.get());
        }
        return createOrder(user, cellphone);
    }

    private ResponseOrderDTO removeItemFromOrder(Cellphone cellphone, Order order) {
        order.getItems().remove(cellphone);
        orderRepository.save(order);
        return orderMapper.fromRequestToResponseOrderDTO(order, "El item fue eliminado exitosamente.");
    }

    private ResponseOrderDTO createOrder(User user, Cellphone cellphone) {
        Order order = Order.builder()
                .orderId(UUID.randomUUID())
                .user(user)
                .status(OrderStatus.CREATING.getStatus())
                .total(cellphone.getPrice())
                .build();
        order.getItems().add(cellphone);
        orderRepository.save(order);
        return orderMapper.fromOrderToResponseOrderDTO(order);
    }

    public ResponseOrderDTO addItemToOrder(Cellphone item, Order order) {
        order.getItems().add(item);
        orderRepository.save(order);
        return orderMapper.fromRequestToResponseOrderDTO(order, "El item fue añadido exitosamente.");
    }

    public void checkPermissions() {
        var currentRole = CellphoneSecurityContext.getCurrentUserRole();
        if(currentRole.equals("SHOP")){
            throw exceptionBuilder.noPermissionException(
                    "A shop user can't create orders."
            );
        }
    }

    private Cellphone checkIfItemExists(String itemName) {
        return cellphoneRepository.findByName(itemName).orElseThrow(
                () -> exceptionBuilder.notFoundException(
                        "The item with the specified name does not exists.", itemName));
    }

    public void finalizePurchase(){
        Optional<Order> order = orderRepository.findByStatus("CREATING", CellphoneSecurityContext.getCurrentUserEmail());

    }

    public void getTotalPrice(){

    }

    public void updateItemStock(){

    }

}
