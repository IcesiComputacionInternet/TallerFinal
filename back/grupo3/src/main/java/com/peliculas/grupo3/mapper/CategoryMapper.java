package com.peliculas.grupo3.mapper;


import com.peliculas.grupo3.dto.CategoryDTO;

import com.peliculas.grupo3.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

        Category fromCategoryDTO(CategoryDTO categoryDTO);
}
