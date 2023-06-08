package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.controller.api.ItemApi;
import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseItemDTO;
import co.com.icesi.eShop_Back.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class ItemController implements ItemApi {
    private final ItemService itemService;

    @Override
    public void saveItem(RequestItemDTO itemDTO) {
        itemService.create(itemDTO);
    }

    @Override
    public ResponseItemDTO getItemById(String id) {
        return itemService.get(UUID.fromString(id));
    }

    @Override
    public void deleteItemById(String id) {
        itemService.delete(UUID.fromString(id));
    }
}
