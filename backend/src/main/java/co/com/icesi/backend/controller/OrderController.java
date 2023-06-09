package co.com.icesi.backend.controller;

import co.com.icesi.backend.api.OrderAPI;
import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.request.RequestChangeOrderDTO;
import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import co.com.icesi.backend.model.ShopOrder;
import co.com.icesi.backend.service.OrderService;
import co.com.icesi.backend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static co.com.icesi.backend.api.OrderAPI.BASE_URL_ORDER;

@RestController
@RequestMapping(BASE_URL_ORDER)
@AllArgsConstructor
public class OrderController implements OrderAPI {
    private final OrderService orderService;

    @Override
    public ResponseOrderDTO createOrder(RequestNewOrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @Override
    public ResponseOrderDTO changeOrderStatus(RequestChangeOrderDTO changeOrderDTO) {
        return orderService.changeOrderStatus(changeOrderDTO);
    }

    @Override
    public ResponseOrderDTO getOrder(UUID orderId) {
        return orderService.getOrder(orderId);
    }

    @Override
    public List<ResponseOrderDTO> getAllOrders() {
        return orderService.getOrders();
    }
}
