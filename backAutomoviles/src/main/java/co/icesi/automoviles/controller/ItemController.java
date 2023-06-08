package co.icesi.automoviles.controller;

import co.icesi.automoviles.api.ItemAPI;
import co.icesi.automoviles.dto.ItemCreateDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ItemController implements ItemAPI {
    private final ItemService itemService;
    @Override
    public ItemShowDTO createItem(ItemCreateDTO itemCreateDTO) {
        return itemService.createItem(itemCreateDTO);
    }

    @Override
    public ItemShowDTO updateItem(String itemId, ItemCreateDTO itemCreateDTO) {
        return itemService.updateItem(itemId, itemCreateDTO);
    }

    @Override
    public ItemShowDTO getItemById(String itemId) {
        return itemService.getItemById(itemId);
    }

    @Override
    public Page<ItemShowDTO> getItems() {
        return null;
    }
}
