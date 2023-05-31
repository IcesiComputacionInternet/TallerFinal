package co.com.icesi.Eshop.api;

import co.com.icesi.Eshop.dto.request.OrderDTO;
import co.com.icesi.Eshop.dto.response.OrderResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(OrderApi.BASE_URL)
public interface OrderApi {
    String BASE_URL = "api/orders";

    @PostMapping("/create")
    OrderResponseDTO createOrder(OrderDTO orderResponseDTO);

    @PostMapping("/update")
    OrderResponseDTO updateOrder(OrderDTO orderResponseDTO);

    @PostMapping("/cancel")
    OrderResponseDTO cancelOrder(OrderDTO orderResponseDTO);

    @PostMapping("/pay")
    OrderResponseDTO payOrder(OrderDTO orderResponseDTO);

    @PostMapping("/deliver")
    OrderResponseDTO deliverOrder(OrderDTO orderResponseDTO);

    @PostMapping("/receive")
    OrderResponseDTO receiveOrder(OrderDTO orderResponseDTO);

    @GetMapping("/all")
    List<OrderResponseDTO> getAllOrders();
}
