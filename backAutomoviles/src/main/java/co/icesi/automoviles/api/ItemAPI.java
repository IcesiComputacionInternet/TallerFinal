package co.icesi.automoviles.api;

import co.icesi.automoviles.dto.ItemCreateDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PageResponse<ItemShowDTO>> getItems(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "per_page", defaultValue = "5") Integer perPage,
            @RequestParam(name = "sort", defaultValue = "name") String sortBy,
            @RequestParam(name = "sort_dir", defaultValue = "asc") String sortDirection
    );

    @DeleteMapping("{itemId}")
    public void deleteItemById(@PathVariable("itemId") String itemId);
}
