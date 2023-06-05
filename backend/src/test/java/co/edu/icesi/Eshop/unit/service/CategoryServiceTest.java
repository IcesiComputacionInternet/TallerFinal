package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.error.exception.EShopException;
import co.edu.icesi.Eshop.mapper.CategoryMapper;
import co.edu.icesi.Eshop.mapper.CategoryMapperImpl;
import co.edu.icesi.Eshop.model.Category;
import co.edu.icesi.Eshop.repository.CategoryRepository;
import co.edu.icesi.Eshop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private CategoryService categoryService;

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    @BeforeEach
    private void init(){
        categoryRepository = mock(CategoryRepository.class);
        categoryMapper = spy(CategoryMapperImpl.class);
        categoryService = new CategoryService(categoryRepository, categoryMapper);
    }

    @Test
    @DisplayName("Category created")
    public void testCreateCategory(){
        var category = defaultCategory();
        when(categoryRepository.findByName(any())).thenReturn(Optional.empty());

        categoryService.save(categoryMapper.fromCategory(category));

        verify(categoryRepository, times(1)).findByName(any());
        verify(categoryRepository, times(1)).save(any());
        verify(categoryMapper, times(1)).fromCategoryDTO(any());

    }

    @Test
    @DisplayName("Category not created. Duplicated name")
    public void testCreateCategoryWithNameThatAlreadyExists(){
        var category = defaultCategory();
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));

        var exception = assertThrows(EShopException.class, () -> categoryService.save(categoryMapper.fromCategory(category)), "No exception was thrown");

        var error = exception.getError();
        var details = error.getDetails();
        assertEquals(1, details.size());
        var detail = details.get(0);
        assertEquals("ERR_DUPLICATED", detail.getErrorCode(), "Code doesn't match");
        assertEquals("Category with name Preparación de alimentos already exists", detail.getErrorMessage(), "Error message doesn't match");
    }

    private Category defaultCategory(){
        return Category.builder()
                .categoryId(UUID.fromString("d575d4a8-9897-431e-99a7-912a8842ecc5"))
                .name("Preparación de alimentos")
                .description("Categoria con electrodomesticos para hacer comida")
                .build();
    }
}
