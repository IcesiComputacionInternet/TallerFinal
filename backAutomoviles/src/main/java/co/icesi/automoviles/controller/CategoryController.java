package co.icesi.automoviles.controller;

import org.springframework.web.bind.annotation.RestController;

import co.icesi.automoviles.api.CategoryAPI;
import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.CategoryShowDTO;
import co.icesi.automoviles.service.CategoryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryAPI {

    private final CategoryService categoryService;

    @Override
    public CategoryShowDTO registerCategory(CategoryCreateDTO categoryCreateDTO) {
        return categoryService.registerCategory(categoryCreateDTO);
    }

    @Override
    public CategoryShowDTO updateCategory(String categoryId, CategoryCreateDTO categoryCreateDTO) {
        return categoryService.updateCategory(categoryId, categoryCreateDTO);
    }

}
