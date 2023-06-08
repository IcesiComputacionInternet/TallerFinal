package com.example.eshopbackend.mapper;

import com.example.eshopbackend.dto.CategoryDTO;
import com.example.eshopbackend.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromCategoryDTO(CategoryDTO category);
    CategoryDTO fromCategory(Category category);
}
