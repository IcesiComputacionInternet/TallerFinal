package co.com.icesi.eShopBackEnd.controller;

import co.com.icesi.eShopBackEnd.api.CategoryAPI;
import co.com.icesi.eShopBackEnd.dto.AssignCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import co.com.icesi.eShopBackEnd.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryAPI {

    private final CategoryService categoryService;
    @Override
    public CreateCategoryDTO createCategory(CreateCategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @Override
    public ResponseItemDTO assignCategory(AssignCategoryDTO assignCategoryDTO) {
        return categoryService.assignCategory(assignCategoryDTO);
    }
    @Override
    public List<ResponseItemDTO> getItemsByCategory(String categoryName) {
        return categoryService.getItemsByCategory(categoryName);
    }

    @Override
    public List<CreateCategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
