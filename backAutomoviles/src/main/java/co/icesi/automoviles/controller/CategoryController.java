package co.icesi.automoviles.controller;

import co.icesi.automoviles.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PageResponse<CategoryShowDTO>> getCategories(Integer page, Integer perPage, String sortBy, String sortDirection) {
        int indexPage = page - 1;
        Page<CategoryShowDTO> categories = categoryService.getAllCategories(indexPage, perPage, sortBy, sortDirection);
        PageResponse<CategoryShowDTO> response = new PageResponse<>(categories, new CategoryShowDTO[0]);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public CategoryShowDTO registerCategory(CategoryCreateDTO categoryCreateDTO) {
        return categoryService.registerCategory(categoryCreateDTO);
    }

    @Override
    public CategoryShowDTO updateCategory(String categoryId, CategoryCreateDTO categoryCreateDTO) {
        return categoryService.updateCategory(categoryId, categoryCreateDTO);
    }

}
