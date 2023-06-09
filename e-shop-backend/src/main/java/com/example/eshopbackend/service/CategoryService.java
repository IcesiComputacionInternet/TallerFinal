package com.example.eshopbackend.service;

import com.example.eshopbackend.dto.CategoryDTO;
import com.example.eshopbackend.mapper.CategoryMapper;
import com.example.eshopbackend.model.Category;
import com.example.eshopbackend.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryDTO save(CategoryDTO categoryDTO) {
        if(categoryRepository.findByName(categoryDTO.getName()).isPresent()){
            throw new RuntimeException("La categoría " + categoryDTO.getName() + " ya existe.");
        }

        Category category = categoryMapper.fromCategoryDTO(categoryDTO);
        category.setCategoryId(UUID.randomUUID());

        return categoryMapper.fromCategory(categoryRepository.save(category));
    }

    public CategoryDTO update(String categoryId, CategoryDTO categoryDTO){
        Category category = categoryRepository.findById(UUID.fromString(categoryId)).orElseThrow(
                () -> new RuntimeException("La categoría con el id " + categoryId + " no existe.")
        );

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return categoryMapper.fromCategory(categoryRepository.save(category));
    }
}
