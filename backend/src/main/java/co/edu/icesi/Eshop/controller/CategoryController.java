package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.CategoryAPI;
import co.edu.icesi.Eshop.dto.CategoryDTO;
import co.edu.icesi.Eshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.Eshop.api.CategoryAPI.BASE_CATEGORY_URL;

@RestController
@RequestMapping(BASE_CATEGORY_URL)
@AllArgsConstructor
public class CategoryController implements CategoryAPI {

    private final CategoryService categoryService;

    @Override
    public CategoryDTO getCategory(String categoryName) {
        return categoryService.getCategoryByName(categoryName);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }
}
