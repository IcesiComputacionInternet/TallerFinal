package co.com.icesi.Eshop.api;

import co.com.icesi.Eshop.dto.request.ItemDTO;
import co.com.icesi.Eshop.dto.response.ItemResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ItemApi.BASE_URL)
public interface ItemApi {
    String BASE_URL = "api/items";

    @PostMapping("/create")
    ItemResponseDTO createItem(ItemDTO itemResponseDTO);

    @PostMapping("/update")
    ItemResponseDTO updateItem(ItemDTO itemResponseDTO);

    @PostMapping("/delete")
    String deleteItem(ItemDTO itemResponseDTO);

    @PostMapping("/find")
    ItemResponseDTO findItem(ItemDTO itemResponseDTO);

    @PostMapping("/all")
    Iterable<ItemResponseDTO> allItems();

}
