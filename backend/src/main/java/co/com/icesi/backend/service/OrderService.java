package co.com.icesi.backend.service;

import co.com.icesi.backend.Enum.CategoryType;
import co.com.icesi.backend.Enum.OrderStatus;
import co.com.icesi.backend.Enum.UserRole;
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
            Order currentOrder = order.get();
            return addItemToOrder(cellphone, currentOrder, requestOrderDTO.getAmount());
        }
        return createOrder(user, cellphone);
    }

    private ResponseOrderDTO removeItemFromOrder(Cellphone cellphone, Order order) {
        order.getAmounts().remove(cellphone.getName());
        order.getItems().remove(cellphone);
        orderRepository.save(order);
        return orderMapper.fromRequestToResponseOrderDTO(order, "Item was deleted successfully.");
    }

    private ResponseOrderDTO createOrder(User user, Cellphone cellphone) {
        Order order = Order.builder()
                .orderId(UUID.randomUUID())
                .user(user)
                .status(OrderStatus.CREATING.getStatus())
                .total(0L)
                .build();
        order.getItems().add(cellphone);
        orderRepository.save(order);
        return orderMapper.fromOrderToResponseOrderDTO(order);
    }

    public ResponseOrderDTO addItemToOrder(Cellphone item, Order order, int amount) {
        order.getAmounts().put(item.getName(), amount);
        order.getItems().add(item);
        orderRepository.save(order);
        return orderMapper.fromRequestToResponseOrderDTO(order, "Item was added successfully.");
    }

    private Cellphone checkIfItemExists(String itemName) {
        return cellphoneRepository.findByName(itemName).orElseThrow(
                () -> exceptionBuilder.notFoundException(
                        "The item with the specified name does not exists.", itemName));
    }

    public ResponseOrderDTO finalizePurchase(RequestOrderItemDTO orderDTO){
        Optional<Order> order = orderRepository.findByStatus("CREATING", CellphoneSecurityContext.getCurrentUserEmail());
        if(order.isPresent()){
            Order currentOrder = order.get();
            currentOrder
                    .getItems()
                    .forEach(item -> item
                            .setStock(currentOrder
                                    .getAmounts()
                                    .get(item.getName())));
            currentOrder.setTotal(getTotalPrice(currentOrder));
            currentOrder.setStatus(OrderStatus.IN_PROCESS.getStatus());
            orderRepository.save(currentOrder);
            return orderMapper.fromRequestToResponseOrderDTO(currentOrder, "Order was created successfully");
        }
        return orderMapper.fromRequestToResponseOrderDTO(orderDTO, "Order was not created successfully");
    }

    public Long getTotalPrice(Order order){
        return order
                .getItems()
                .stream()
                .mapToLong(item -> order
                        .getAmounts()
                        .get(item.getName()) * item.getPrice())
                .sum();
    }

    public ResponseOrderDTO changeOrderStatus(RequestOrderItemDTO orderItemDTO){
        checkPermissionToChangeStatus();
        String status = orderItemDTO.getStatus();
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> exceptionBuilder.notFoundException(
                        "The order with the specified id does not exists.", orderItemDTO.getOrderId().toString()));

        if(!OrderStatus.isStringEqualToEnum(status)){
            throw exceptionBuilder.notFoundException("The status is not valid", orderItemDTO.getStatus());
        }

        order.setStatus(status);
        orderRepository.save(order);
        return orderMapper.fromRequestToResponseOrderDTO(orderItemDTO, "Status successfully updated");
    }

    private void checkPermissionToChangeStatus() {
        var currentRole = CellphoneSecurityContext.getCurrentUserRole();
        if(currentRole.equals(UserRole.USER.getRole())){
            throw exceptionBuilder.noPermissionException(
                    "No permission to change the status order."
            );
        }
    }

    public void checkPermissions() {
        var currentRole = CellphoneSecurityContext.getCurrentUserRole();
        if(currentRole.equals("SHOP")){
            throw exceptionBuilder.noPermissionException(
                    "A shop user can't create orders."
            );
        }
    }
}
