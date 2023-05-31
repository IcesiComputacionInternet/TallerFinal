package co.com.icesi.Eshop.controller;

import co.com.icesi.Eshop.api.OrderApi;
import co.com.icesi.Eshop.dto.request.OrderDTO;
import co.com.icesi.Eshop.dto.response.OrderResponseDTO;
import co.com.icesi.Eshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;
    /**
     * @param orderDTO
     * @return
     */
    @Override
    public OrderResponseDTO createOrder(OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    /**
     * @param orderDTO
     * @return
     */
    @Override
    public OrderResponseDTO updateOrder(OrderDTO orderDTO) {
        return null;
    }

    /**
     * @param orderDTO
     * @return
     */
    @Override
    public OrderResponseDTO cancelOrder(OrderDTO orderDTO) {
        return null;
    }

    /**
     * @param orderDTO
     * @return
     */
    @Override
    public OrderResponseDTO payOrder(OrderDTO orderDTO) {
        return null;
    }

    /**
     * @param orderDTO
     * @return
     */
    @Override
    public OrderResponseDTO deliverOrder(OrderDTO orderDTO) {
        return null;
    }

    /**
     * @param orderDTO
     * @return
     */
    @Override
    public OrderResponseDTO receiveOrder(OrderDTO orderDTO) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
}
