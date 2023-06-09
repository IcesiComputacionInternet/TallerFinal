package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.controller.api.OrderApi;
import co.com.icesi.eShop_Back.dto.request.RequestOrderDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseOrderDTO;
import co.com.icesi.eShop_Back.enums.Status;
import co.com.icesi.eShop_Back.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class OrderController implements OrderApi {
    private final OrderService orderService;
    @Override
    public void save(RequestOrderDTO orderDTO) {
        orderService.create(orderDTO);
    }

    @Override
    public ResponseOrderDTO getById(String id) {
        return orderService.get(UUID.fromString(id));
    }

    @Override
    public ResponseOrderDTO getByUserEmail(String email) {
        return orderService.getByUser(email);
    }

    @Override
    public List<ResponseOrderDTO> getAll() {
        return orderService.getAll();
    }

    @Override
    public void deleteById(String id) {
        orderService.delete(UUID.fromString(id));
    }

    @Override
    public void pay(String id) {
        orderService.updateStatus(UUID.fromString(id), Status.PAID);
    }

    @Override
    public void cancel(String id) {
        orderService.updateStatus(UUID.fromString(id), Status.CANCELLED);
    }

    @Override
    public void deliver(String id) {
        orderService.updateStatus(UUID.fromString(id), Status.DELIVERED);
    }

    @Override
    public void done(String id) {
        orderService.updateStatus(UUID.fromString(id), Status.DONE);
    }
}
