package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.mapper.CategoryMapper;
import co.edu.icesi.Eshop.mapper.CategoryMapperImpl;
import co.edu.icesi.Eshop.repository.CategoryRepository;
import co.edu.icesi.Eshop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

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
}
