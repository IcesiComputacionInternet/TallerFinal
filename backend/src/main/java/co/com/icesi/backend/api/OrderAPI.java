package co.com.icesi.backend.api;

import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController(OrderAPI.BASE_URL_ORDER)
public interface OrderAPI {
    String BASE_URL_ORDER = "/orders";
}
