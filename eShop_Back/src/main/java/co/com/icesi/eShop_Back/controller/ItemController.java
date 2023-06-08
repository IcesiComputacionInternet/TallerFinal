package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.controller.api.ItemApi;
import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseItemDTO;
import co.com.icesi.eShop_Back.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ItemController implements ItemApi {
    private final ItemService itemService;

    @Override
    public void save(RequestItemDTO itemDTO) {
        itemService.create(itemDTO);
    }

    @Override
    public ResponseItemDTO getById(String id) {
        return itemService.get(UUID.fromString(id));
    }

    @Override
    public List<ResponseItemDTO> getAll() {
        return itemService.getAll();
    }

    @Override
    public void deleteById(String id) {
        itemService.delete(UUID.fromString(id));
    }

    @Override
    public void update(RequestItemDTO itemDTO, String id) {
        itemService.update(itemDTO, UUID.fromString(id));
    }
}
