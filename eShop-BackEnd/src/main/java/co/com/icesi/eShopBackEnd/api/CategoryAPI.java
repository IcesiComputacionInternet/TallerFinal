package co.com.icesi.eShopBackEnd.api;

import co.com.icesi.eShopBackEnd.dto.AssignCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static co.com.icesi.eShopBackEnd.api.CategoryAPI.BASE_CATEGORY_URL;


@RequestMapping(value = BASE_CATEGORY_URL)
public interface CategoryAPI {
    String BASE_CATEGORY_URL = "/category";

    @PostMapping
    CreateCategoryDTO createCategory(@Valid @RequestBody CreateCategoryDTO categoryDTO);

    @PatchMapping("/assignCategory/")
    ResponseItemDTO assignCategory(@Valid @RequestBody AssignCategoryDTO assignCategoryDTO);

    @GetMapping("/itemsByCategory/{categoryName}")
    List<ResponseItemDTO> getItemsByCategory(@PathVariable String categoryName);

    @GetMapping("/all")
    List<CreateCategoryDTO> getAllCategories();

}
