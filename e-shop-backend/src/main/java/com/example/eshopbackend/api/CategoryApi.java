package com.example.eshopbackend.api;

import com.example.eshopbackend.dto.CategoryDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(CategoryApi.CATEGORY_BASE_URL)
public interface CategoryApi {

    String CATEGORY_BASE_URL = "/categories";

    @PostMapping("/add")
    void createCategory(@RequestBody @Valid CategoryDTO categoryDTO);

    @GetMapping("/getAll")
    List<CategoryDTO> getAll();

    @PatchMapping("/update/{categoryId}")
    void updateCategory(@PathVariable String categoryId, @RequestBody @Valid CategoryDTO categoryDTO);
}
