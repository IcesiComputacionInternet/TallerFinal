package com.icesi.backend.controllers;

import com.icesi.backend.DTO.ItemCreateDTO;
import com.icesi.backend.error.exception.EShopError;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.errorConstants.BackendApplicationErrors;
import com.icesi.backend.models.Item;
import com.icesi.backend.service.impl.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController()
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public Item createItem(@Valid @RequestBody ItemCreateDTO itemCreateDTO) {
        Optional<Item> createdItem = itemService.createItem(itemCreateDTO);
        if (createdItem.isPresent()) {
            return createdItem.get();
        } else {
            throw new EShopException(HttpStatus.NOT_FOUND, new EShopError(BackendApplicationErrors.CODE_O_01, BackendApplicationErrors.CODE_O_01.getMessage()));
        }
    }
}
