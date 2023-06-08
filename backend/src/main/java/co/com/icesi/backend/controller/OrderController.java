package co.com.icesi.backend.controller;

import co.com.icesi.backend.api.OrderAPI;
import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.request.RoleDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import co.com.icesi.backend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class OrderController implements OrderAPI {
    private final RoleService roleService;

    @Override
    public ResponseCellphoneDTO createCellphone(RequestCellphoneDTO cellphoneDTO) {
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
