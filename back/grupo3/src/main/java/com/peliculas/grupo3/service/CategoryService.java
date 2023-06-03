package com.peliculas.grupo3.service;

import com.peliculas.grupo3.dto.CategoryDTO;
import com.peliculas.grupo3.mapper.CategoryMapper;
import com.peliculas.grupo3.model.Category;
import com.peliculas.grupo3.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryDTO save(CategoryDTO categoryDTO) {
        if(categoryRepository.findByName(categoryDTO.getName()).isPresent()){
            throw new RuntimeException("Category already exists");
        }

        String pgRating= categoryDTO.getPgRating();

        if(pgRating.equals("G") || pgRating.equals("PG") || pgRating.equals("PG-13") || pgRating.equals("R") || pgRating.equals("NC-17")){
            throw new RuntimeException("Category already exists");
        }

        Category category = categoryMapper.fromCategoryDTO(categoryDTO);
        category.setCategoryId(UUID.randomUUID());
        categoryRepository.save(category);

        return categoryDTO;
    }
}
