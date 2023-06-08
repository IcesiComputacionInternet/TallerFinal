package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseItemDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ItemApi.ITEM_BASE_URI)
public interface ItemApi {
    String ITEM_BASE_URI = "/api/v1/items";

    @PostMapping
    void saveItem(@RequestBody RequestItemDTO itemDTO);

    @GetMapping("/get/id/{id}")
    ResponseItemDTO getItemById(@PathVariable("id") String id);

    @DeleteMapping("/delete/id/{id}")
    void deleteItemById(@PathVariable("id") String id);
}
