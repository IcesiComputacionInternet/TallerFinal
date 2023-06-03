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

    @Override
    public OrderResponseDTO delete(String orderName) {
        return orderService.deleteOrder(orderName);
    }

    /**
     * @param orderDTO
     * @return
     * public OrderResponseDTO cancelOrder(OrderDTO orderDTO) {
     *         return null;
     *     }
     */



    /**
     * @param orderID
     * @return
     */
    @Override
    public OrderResponseDTO payOrder(String orderID) {
        return null;
    }

    /**
     * @param orderID
     * @return
     */
    @Override
    public OrderResponseDTO deliverOrder(String orderID) {
        return null;
    }

    /**
     * @param orderID
     * @return
     */
    @Override
    public OrderResponseDTO receiveOrder(String orderID) {
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
