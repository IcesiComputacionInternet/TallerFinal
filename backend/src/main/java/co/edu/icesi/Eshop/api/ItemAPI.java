package co.edu.icesi.Eshop.api;

import co.edu.icesi.Eshop.dto.ItemDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ItemAPI {

    String BASE_ITEM_URL = "/items";

    @GetMapping("/{name}")
    ItemDTO getItemByName(@PathVariable String name);

    @PostMapping
    ItemDTO createItem(@Valid @RequestBody ItemDTO itemDTO);

    @PutMapping("/setState/{name}")
    ItemDTO setItemState(@PathVariable String name);

    @PutMapping("/setPrice/{name}")
    ItemDTO setItemPrice(@PathVariable String name, Long price);

    @GetMapping
    List<ItemDTO> getAllItems();
}
