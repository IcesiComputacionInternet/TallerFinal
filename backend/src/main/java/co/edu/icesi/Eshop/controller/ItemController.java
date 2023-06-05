package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.ItemAPI;
import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.Eshop.api.ItemAPI.BASE_ITEM_URL;

@RequestMapping(BASE_ITEM_URL)
@RestController
@AllArgsConstructor
@CrossOrigin
public class ItemController implements ItemAPI {

    private final ItemService itemService;

    @Override
    public ItemDTO getItemByName(String name) {
        return itemService.getItemByName(name);
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        return itemService.save(itemDTO);
    }

    @Override
    public ItemDTO setItemState(String name) {
        return itemService.setItemState(name);
    }

    @Override
    public ItemDTO setItemPrice(String name, Long price) {
        return itemService.setItemPrice(name, price);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }
}
