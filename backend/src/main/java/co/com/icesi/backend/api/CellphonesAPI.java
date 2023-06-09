package co.com.icesi.backend.api;

import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(CellphonesAPI.BASE_CELLPHONE_URL)
public interface CellphonesAPI {
    String BASE_CELLPHONE_URL = "/cellphones";
    @PostMapping("/create")
    ResponseCellphoneDTO createCellphone(@RequestBody @Valid RequestCellphoneDTO cellphoneDTO);
    @GetMapping("/{id}")
    ResponseCellphoneDTO getCellphone(@PathVariable("id")UUID cellphoneId);
    @GetMapping("/getAccounts")
    List<ResponseCellphoneDTO> getAllCellphones();
}
