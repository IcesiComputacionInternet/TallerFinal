package co.com.icesi.backend.unit.service;

import co.com.icesi.backend.Enum.CategoryType;
import co.com.icesi.backend.dto.request.CategoryDTO;
import co.com.icesi.backend.error.exception.CellphoneException;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.CategoryMapper;
import co.com.icesi.backend.mapper.CategoryMapperImpl;
import co.com.icesi.backend.model.Category;
import co.com.icesi.backend.repository.CategoryRepository;
import co.com.icesi.backend.service.CategoryService;
import co.com.icesi.backend.unit.service.matcher.CategoryMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private CellphoneShopExceptionBuilder exceptionBuilder;

    @BeforeEach
    private void init(){
        categoryRepository = mock(CategoryRepository.class);
        categoryMapper = spy(CategoryMapperImpl.class);
        categoryService = new CategoryService(categoryRepository, categoryMapper);
        categoryService = spy(categoryService);
    }
    
    @Test
    public void testCreateCategory_HappyPath(){
        doNothing().when(categoryService).checkPermissions();
        categoryService.save(defaultCategoryDTO());
        Category category = defaultCategory();

        verify(categoryRepository, times(1)).save(argThat(new CategoryMatcher(category)));
        verify(categoryMapper, times(1)).fromCategoryDTO(any());
        verify(categoryMapper, times(1)).fromCategoryToCategoryDTO(any());
        verify(categoryRepository, times(1)).isNameInUse(any());
    }

    @Test
    public void testCreateCategoryWhenNameAlreadyExists(){
        doNothing().when(categoryService).checkPermissions();
        when(categoryRepository.isNameInUse(any())).thenReturn(true);

        try{
            categoryService.save(defaultCategoryDTO());
            fail();
        }catch (CellphoneException exception){
            String message = exception.getMessage();
            var error = exception.getError();
            var details = error.getDetails();
            var detail = details.get(0);

            assertEquals(1, details.size());
            assertEquals("ERR_DUPLICATED", detail.getErrorCode(), "Code doesn't match");
            assertEquals("LOW_MED_RANGE, already exists.", detail.getErrorMessage(), "Error message doesn't match");
            assertEquals("Another category already has this name.", message);
        }
    }

    private Category defaultCategory() {
        return Category.builder()
                .categoryId(UUID.randomUUID())
                .name("LOW_MED_RANGE")
                .description("Loreno Insomnio, I never knew how to say it")
                .build();
    }

    private CategoryDTO defaultCategoryDTO() {
        return CategoryDTO.builder()
                .name("LOW_MED_RANGE")
                .description("Loreno Insomnio, I never knew how to say it")
                .build();
    }
}
