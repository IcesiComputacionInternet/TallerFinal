package co.com.icesi.backend.controller;

import co.com.icesi.backend.api.OrderAPI;
import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import co.com.icesi.backend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static co.com.icesi.backend.api.OrderAPI.BASE_URL_ORDER;

@RestController
@RequestMapping(BASE_URL_ORDER)
@AllArgsConstructor
public class OrderController implements OrderAPI {
    private final RoleService roleService;

    @Override
    public ResponseCellphoneDTO createCellphone(RequestCellphoneDTO cellphoneDTO) {
        return null;
    }

    @Override
    public ResponseCellphoneDTO cOrder(RequestNewOrderDTO orderItemDTO) {
        return null;
    }

    @Override
    public ResponseCellphoneDTO getCellphone(UUID cellphoneId) {
        return null;
    }

    @Override
    public List<ResponseCellphoneDTO> getAllCellphones() {
        return null;
    }
}
