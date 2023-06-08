package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseItemDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(ItemApi.ITEM_BASE_URI)
public interface ItemApi {
    String ITEM_BASE_URI = "/api/items";

    @PostMapping
    void save(@RequestBody @Valid RequestItemDTO itemDTO);

    @GetMapping("/get/{id}")
    ResponseItemDTO getById(@PathVariable("id") String id);
    @GetMapping("/get/all")
    List<ResponseItemDTO> getAll();

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable("id") String id);

    @PostMapping("/update/{id}")
    void update(@RequestBody @Valid RequestItemDTO itemDTO, @PathVariable("id") String id);
}
