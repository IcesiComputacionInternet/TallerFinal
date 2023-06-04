package co.com.icesi.backend.controller;

import co.com.icesi.backend.api.CategoryAPI;
import co.com.icesi.backend.dto.request.CategoryDTO;
import co.com.icesi.backend.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryAPI {
    private final CategoryService categoryService;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @Override
    public CategoryDTO getCategory(String categoryName) {
        return categoryService.getCategory(categoryName);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
