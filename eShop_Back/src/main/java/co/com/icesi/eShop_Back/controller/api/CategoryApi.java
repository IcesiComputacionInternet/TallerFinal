package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.request.RequestCategoryDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseCategoryDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(CategoryApi.CATEGORY_BASE_URI)
public interface CategoryApi {
    String CATEGORY_BASE_URI = "/api/v1/categories";
    @PostMapping
    void saveCategory(@RequestBody @Valid RequestCategoryDTO categoryDTO);
    @GetMapping("/get/id/{id}")
    ResponseCategoryDTO getCategoryById(@PathVariable("id") String id);
    @DeleteMapping("/delete/id/{id}")
    void deleteCategoryById(@PathVariable("id") String id);
    @GetMapping("/get/all")
    List<ResponseCategoryDTO> findAll();
}
