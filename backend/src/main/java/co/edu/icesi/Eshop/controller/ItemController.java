package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.ItemAPI;
import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static co.edu.icesi.Eshop.api.ItemAPI.BASE_ITEM_URL;

@RequestMapping(BASE_ITEM_URL)
@RestController
@AllArgsConstructor
public class ItemController implements ItemAPI {

    private final ItemService itemService;

    @Override
    public ItemDTO getItemByName(String name) {
        return itemService.getItemByName(name);
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        return null;
    }
}
