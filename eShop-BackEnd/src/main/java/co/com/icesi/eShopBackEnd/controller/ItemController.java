package co.com.icesi.eShopBackEnd.controller;

import co.com.icesi.eShopBackEnd.api.ItemAPI;
import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import co.com.icesi.eShopBackEnd.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ItemController implements ItemAPI {

    private final ItemService itemService;

    @Override
    public ResponseItemDTO createItem(CreateItemDTO itemDTO) {
        return itemService.save(itemDTO);
    }

    @Override
    public ResponseItemDTO getItemByName(String itemName) {
        return itemService.getItemByName(itemName);
    }

    @Override
    public List<ResponseItemDTO> getAllItems() {
        return itemService.getAllItems();
    }
}
