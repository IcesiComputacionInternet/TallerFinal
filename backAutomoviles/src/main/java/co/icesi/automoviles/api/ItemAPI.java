package co.icesi.automoviles.api;

import co.icesi.automoviles.dto.ItemCreateDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/items")
public interface ItemAPI {
    public static final String ROOT_PATH = "/items";

    @PostMapping
    public ItemShowDTO createItem(@Valid @RequestBody ItemCreateDTO itemCreateDTO);

    @PatchMapping("{itemId}")
    public ItemShowDTO updateItem(@PathVariable("itemId") String itemId, @Valid @RequestBody ItemCreateDTO itemCreateDTO);

    @GetMapping("{itemId}")
    public ItemShowDTO getItemById(@PathVariable("itemId") String itemId);

    @GetMapping
    public List<ItemShowDTO> getItems();
}
