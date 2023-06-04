package co.edu.icesi.Eshop.api;

import co.edu.icesi.Eshop.dto.ItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ItemAPI {

    String BASE_ITEM_URL = "/items";

    @GetMapping("/{name}")
    ItemDTO getItemByName(@PathVariable String name);

    @PostMapping("/create")
    ItemDTO createItem(@Valid @RequestBody ItemDTO itemDTO);
}
