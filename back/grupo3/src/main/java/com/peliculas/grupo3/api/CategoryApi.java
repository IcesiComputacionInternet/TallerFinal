package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.CategoryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.peliculas.grupo3.api.CategoryApi.BASE_CATEGORY_URL;

@RequestMapping(value = BASE_CATEGORY_URL)
public interface CategoryApi {
    String BASE_CATEGORY_URL = "/categories";


    @PostMapping("/")
    CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO);

    @GetMapping("/all")
    List<CategoryDTO> getAllCategories();
}
