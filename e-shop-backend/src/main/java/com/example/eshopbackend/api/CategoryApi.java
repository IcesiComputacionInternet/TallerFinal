package com.example.eshopbackend.api;

import com.example.eshopbackend.dto.CategoryDTO;
import com.example.eshopbackend.dto.RoleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(CategoryApi.CATEGORY_BASE_URL)
public interface CategoryApi {

    String CATEGORY_BASE_URL = "/categories";

    @PostMapping("/add")
    void createCategory(@RequestBody @Valid CategoryDTO categoryDTO);
}
