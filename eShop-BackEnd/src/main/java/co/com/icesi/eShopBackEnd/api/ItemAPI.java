package co.com.icesi.eShopBackEnd.api;

import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

import static co.com.icesi.eShopBackEnd.api.ItemAPI.BASE_ITEM_URL;


@RequestMapping(value = BASE_ITEM_URL)
public interface ItemAPI {

    String BASE_ITEM_URL = "/item";

    @PostMapping
    ResponseItemDTO createItem(@Valid @RequestBody CreateItemDTO itemDTO);

    @GetMapping("/{itemName}")
    ResponseItemDTO getItemByName(@PathVariable String itemName);

    @GetMapping("/all")
    List<ResponseItemDTO> getAllItems();

}
