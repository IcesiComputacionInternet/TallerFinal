package co.com.icesi.backend.service;

import co.com.icesi.backend.Enum.OrderStatus;
import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.RequestChangeOrderDTO;
import co.com.icesi.backend.dto.request.RequestOrderItemDTO;
import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.CellphoneMapper;
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

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CellphoneRepository cellphoneRepository;
    private final OrderMapper orderMapper;
    private final CellphoneMapper cellphoneMapper;
    private final CellphoneShopExceptionBuilder exceptionBuilder = new CellphoneShopExceptionBuilder();

    private ResponseOrderDTO saveOrder(RequestNewOrderDTO requestOrderDTO) {
        checkPermissionsToCreate();
        var userEmail = CellphoneSecurityContext.getCurrentUserEmail();
        User user =  userRepository.findByEmail(userEmail).orElseThrow(
                () -> exceptionBuilder.notFoundException(
                        "The user with the specified email does not exists.", userEmail));
        List<Cellphone> items = requestOrderDTO.getItems()
                .stream()
                .map(this::checkIfItemExists)
                .collect(Collectors.toList());
        List<ResponseCellphoneDTO> cellphones = items.stream()
                .map(cellphoneMapper::fromCellphoneToResponseCellphoneDTO)
                .collect(Collectors.toList());
        Set<Integer> quantities = requestOrderDTO.getItems()
                .stream()
                .map(RequestOrderItemDTO::getQuantity)
                .collect(Collectors.toSet());
        Order order = orderMapper.fromRequestOrderDTO(requestOrderDTO);
        order.setUser(user);
        order.setOrderId(UUID.randomUUID());
        order.setStatus(OrderStatus.IN_PROCESS);
        order.setItems(items);
        order.setQuantities(quantities);
        orderRepository.save(order);
        ResponseOrderDTO responseOrderDTO =orderMapper.fromOrderToResponseOrderDTO(order);
        responseOrderDTO.setItems(cellphones);
        responseOrderDTO.setMessage("Order successfully created");
        return responseOrderDTO;
    }

    private Cellphone checkIfItemExists(RequestOrderItemDTO item) {
        return cellphoneRepository.findById(item.getId()).orElseThrow(
                () -> exceptionBuilder.notFoundException(
                        "The item with the specified ID does not exists.", item.getId().toString()));
    }

    public ResponseOrderDTO changeOrderStatus(RequestChangeOrderDTO requestChangeOrderDTO){
        checkPermissionToChangeStatus();
        Order order = orderRepository.findById(requestChangeOrderDTO.getOrderId()).orElseThrow(
                () -> exceptionBuilder.notFoundException(
                        "The order with the specified ID does not exists.", requestChangeOrderDTO.getOrderId().toString()
                )
        );
        if(!OrderStatus.isStringEqualToEnum(requestChangeOrderDTO.getNewStatus())){
            throw exceptionBuilder.notFoundException("The status is not valid", requestChangeOrderDTO.getNewStatus());
        }
        order.setStatus(OrderStatus.valueOf(requestChangeOrderDTO.getNewStatus()));
        orderRepository.save(order);
        return orderMapper.fromRequestChangeToResponseOrderDTO(order, "Status successfully updated");
    }

    private void checkPermissionToChangeStatus() {
        var currentRole = CellphoneSecurityContext.getCurrentUserRole();
        if(currentRole.equals(UserRole.USER.getRole())){
            throw exceptionBuilder.noPermissionException(
                    "No permission to change the status order."
            );
        }
    }

    public void checkPermissionsToCreate() {
        var currentRole = CellphoneSecurityContext.getCurrentUserRole();
        if(currentRole.equals("SHOP")){
            throw exceptionBuilder.noPermissionException(
                    "A shop user can't create orders."
            );
        }
    }
}
