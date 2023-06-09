package com.example.eshopbackend.api;

import com.example.eshopbackend.dto.CategoryDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(CategoryApi.CATEGORY_BASE_URL)
public interface CategoryApi {

    String CATEGORY_BASE_URL = "/categories";

    @PostMapping("/add")
    void createCategory(@RequestBody @Valid CategoryDTO categoryDTO);

    @PatchMapping("/update/{categoryId}")
    void updateCategory(@PathVariable String categoryId, @RequestBody @Valid CategoryDTO categoryDTO);
}
