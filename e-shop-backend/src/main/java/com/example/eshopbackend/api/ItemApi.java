package com.example.eshopbackend.api;

import com.example.eshopbackend.dto.ItemDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(ItemApi.ITEM_BASE_URL)
public interface ItemApi {
    String ITEM_BASE_URL = "/items";

    @PostMapping("/add")
    void createItem(@RequestBody @Valid ItemDTO itemDTO);

    @GetMapping("/getAll")
    List<ItemDTO> getAll();

    @GetMapping("get/{id}")
    ItemDTO getById(@PathVariable("id") String id);
}
