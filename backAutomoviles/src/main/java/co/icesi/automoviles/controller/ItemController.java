package co.icesi.automoviles.controller;

import co.icesi.automoviles.api.ItemAPI;
import co.icesi.automoviles.dto.ItemCreateDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.dto.PageResponse;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PageResponse<ItemShowDTO>> getItems(Integer page, Integer perPage, String sortBy, String sortDirection) {
        int indexPage = page - 1;
        Page<ItemShowDTO> items;
        items = itemService.getAllItems(indexPage, perPage, sortBy, sortDirection);
        PageResponse<ItemShowDTO> response = new PageResponse<>(items, new ItemShowDTO[0]);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public void deleteItemById(String itemId) {
        itemService.deleteItemById(itemId);
    }
}
