package co.com.icesi.backend.controller;

import co.com.icesi.backend.api.CellphonesAPI;
import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import co.com.icesi.backend.service.CellphoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static co.com.icesi.backend.api.CellphonesAPI.BASE_CELLPHONE_URL;

@RestController
@RequestMapping(BASE_CELLPHONE_URL)
@AllArgsConstructor
public class CellphoneController implements CellphonesAPI {
    private final CellphoneService cellphoneService;

    @Override
    public ResponseCellphoneDTO createCellphone(RequestCellphoneDTO cellphoneDTO) {
        return cellphoneService.save(cellphoneDTO);
    }

    @Override
    public ResponseCellphoneDTO getCellphone(UUID cellphoneId) {
        return cellphoneService.getCellphone(cellphoneId);
    }

    @Override
    public List<ResponseCellphoneDTO> getAllCellphones() {
        return cellphoneService.getAllCellphones();
    }
}
