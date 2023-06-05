package co.edu.icesi.Eshop.api;


import co.edu.icesi.Eshop.dto.ChangeStatusDTO;
import co.edu.icesi.Eshop.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{orderId}")
    OrderDTO changeStatus(@Valid @RequestBody ChangeStatusDTO changeStatusDTO);
}
