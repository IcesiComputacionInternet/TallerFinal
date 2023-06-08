package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.controller.api.CategoryApi;
import co.com.icesi.eShop_Back.dto.request.RequestCategoryDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseCategoryDTO;
import co.com.icesi.eShop_Back.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;

    @Override
    public void saveCategory(RequestCategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
    }

    @Override
    public ResponseCategoryDTO getCategoryById(String id) {
        return categoryService.get(UUID.fromString(id));
    }

    @Override
    public void deleteCategoryById(String id) {
        categoryService.delete(UUID.fromString(id));
    }

    @Override
    public List<ResponseCategoryDTO> findAll() {
        return categoryService.getAll();
    }
}
