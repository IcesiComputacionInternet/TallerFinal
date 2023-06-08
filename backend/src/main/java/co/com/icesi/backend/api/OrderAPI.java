package co.com.icesi.backend.api;

import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController(OrderAPI.BASE_URL_ORDER)
public interface OrderAPI {
    String BASE_URL_ORDER = "/orders";

    @PostMapping("/create")
    ResponseCellphoneDTO cOrder(@RequestBody @Valid RequestNewOrderDTO orderItemDTO);
    @GetMapping("/{id}")
    ResponseCellphoneDTO getCellphone(@PathVariable("id") UUID cellphoneId);
    @GetMapping("/getAccounts")
    List<ResponseCellphoneDTO> getAllCellphones();
}
