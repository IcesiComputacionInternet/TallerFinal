package com.icesi.backend.controllers;

import com.icesi.backend.DTO.ItemCreateDTO;
import com.icesi.backend.models.Item;
import com.icesi.backend.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public Item createItem(@RequestBody ItemCreateDTO itemCreateDTO){
        return itemService.createItem(itemCreateDTO).get();
    }
}
