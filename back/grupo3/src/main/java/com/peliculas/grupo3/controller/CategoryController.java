package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.api.CategoryApi;
import com.peliculas.grupo3.dto.CategoryDTO;
import com.peliculas.grupo3.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAll();
    }




}
