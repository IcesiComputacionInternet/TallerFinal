package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.request.RequestChangeStatusOrder;
import co.com.icesi.eShop_Back.dto.request.RequestOrderDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseOrderDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(OrderApi.ORDER_BASE_URL)
public interface OrderApi {
    String ORDER_BASE_URL = "/api/orders";

    @PostMapping
    void save(@RequestBody @Valid RequestOrderDTO orderDTO);

    @GetMapping("/get/{id}")
    ResponseOrderDTO getById(@PathVariable String id);

    @GetMapping("/get/user/{email}")
    ResponseOrderDTO getByUserEmail(@PathVariable String email);

    @GetMapping("/get/all")
    List<ResponseOrderDTO> getAll();

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable String id);

    @PatchMapping("/update/status")
    void updateStatus(@RequestBody @Valid RequestChangeStatusOrder requestChangeStatusOrder);

}
