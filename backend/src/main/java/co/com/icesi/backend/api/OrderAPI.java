package co.com.icesi.backend.api;

import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.request.RequestChangeOrderDTO;
import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController(OrderAPI.BASE_URL_ORDER)
public interface OrderAPI {
    String BASE_URL_ORDER = "/orders";

    @PostMapping("/create")
    ResponseOrderDTO createOrder(@RequestBody @Valid RequestNewOrderDTO orderDTO);
    @PostMapping("/changeStatus")
    ResponseOrderDTO changeOrderStatus(@RequestBody @Valid RequestChangeOrderDTO changeOrderDTO);
    @GetMapping("/{id}")
    ResponseOrderDTO getOrder(@PathVariable("id") UUID orderId);
    @GetMapping("/getAccounts")
    List<ResponseOrderDTO> getAllOrders();
}
