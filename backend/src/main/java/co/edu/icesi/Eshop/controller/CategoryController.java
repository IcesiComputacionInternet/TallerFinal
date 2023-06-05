package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.CategoryAPI;
import co.edu.icesi.Eshop.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.Eshop.api.CategoryAPI.BASE_CATEGORY_URL;

@RestController
@RequestMapping(BASE_CATEGORY_URL)
@AllArgsConstructor
public class CategoryController implements CategoryAPI {
    @Override
    public CategoryDTO getCategory(String categoryName) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return null;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return null;
    }
}
