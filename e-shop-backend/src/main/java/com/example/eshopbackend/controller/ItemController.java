package com.example.eshopbackend.controller;

import com.example.eshopbackend.api.ItemApi;
import com.example.eshopbackend.dto.ItemDTO;
import com.example.eshopbackend.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ItemController implements ItemApi {

    private final ItemService itemService;

    @Override
    public void createItem(ItemDTO itemDTO) {
        itemService.addItem(itemDTO);
    }

    @Override
    public List<ItemDTO> getAll() {
        return itemService.getAllItems();
    }

    @Override
    public ItemDTO getById(String id) {
        return itemService.getItemById(UUID.fromString(id));
    }
}
