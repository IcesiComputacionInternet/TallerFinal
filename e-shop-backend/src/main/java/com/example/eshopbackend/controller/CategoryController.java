package com.example.eshopbackend.controller;

import com.example.eshopbackend.api.CategoryApi;
import com.example.eshopbackend.dto.CategoryDTO;
import com.example.eshopbackend.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;
    @Override
    public void createCategory(CategoryDTO categoryDTO) {

        categoryService.save(categoryDTO);
    }

    @Override
    public void updateCategory(String categoryId, CategoryDTO categoryDTO) {
        categoryService.update(categoryId, categoryDTO);
    }


}
