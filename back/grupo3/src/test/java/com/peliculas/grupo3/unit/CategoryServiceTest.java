package com.peliculas.grupo3.unit;

import com.peliculas.grupo3.dto.CategoryDTO;
import com.peliculas.grupo3.mapper.CategoryMapper;
import com.peliculas.grupo3.mapper.CategoryMapperImpl;
import com.peliculas.grupo3.model.Category;
import com.peliculas.grupo3.repository.CategoryRepository;
import com.peliculas.grupo3.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CategoryServiceTest {

    private CategoryMapper categoryMapper;

    private CategoryService categoryService;

    private CategoryRepository categoryRepository;

    @BeforeEach
    public void init(){
        categoryRepository= mock(CategoryRepository.class);
        categoryMapper = spy(CategoryMapperImpl.class);
        categoryService = new CategoryService(categoryRepository,categoryMapper);
    }

    @Test
    public void testCreateCategory(){
        categoryService.save(defaultCategoryDTO());
        Category role = defaultCategory();
        verify(categoryRepository,times(1)).findByName(argThat(name->name.equals(role.getName())));
    }

    private CategoryDTO defaultCategoryDTO(){
        return CategoryDTO.builder()
                .description("Categoria de prueba")
                .name("nombre de categoria de prueba")
                .build();
    }

    private Category defaultCategory(){
        return Category.builder()
                .description("Categoria de prueba")
                .name("nombre de categoria de prueba")
                .build();
    }

}
