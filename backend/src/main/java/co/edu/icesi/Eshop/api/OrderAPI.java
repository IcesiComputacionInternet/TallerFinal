package co.edu.icesi.Eshop.api;


import co.edu.icesi.Eshop.dto.OrderDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface OrderAPI {

    String BASE_ORDER_URL="/orders";

    @GetMapping("/{orderId}")
    OrderDTO getOrder(@PathVariable String orderId);

    @GetMapping
    List<OrderDTO> getAllOrders();

    @PostMapping
    OrderDTO addOrder(@Valid @RequestBody OrderDTO orderDTO);
}
