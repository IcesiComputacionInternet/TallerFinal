package co.com.icesi.Eshop.Unit.service;

import co.com.icesi.Eshop.Unit.util.CrudTest;
import co.com.icesi.Eshop.mapper.CategoryMapper;
import co.com.icesi.Eshop.mapper.CategoryMapperImpl;
import co.com.icesi.Eshop.repository.CategoryRepository;
import co.com.icesi.Eshop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class CategoryServiceTest  implements CrudTest {
    private CategoryMapper categoryMapper;
    private CategoryService categoryService;
    private  CategoryRepository categoryRepository;

    //TODO: Faltan metodos
    @BeforeEach
    public  void init(){
        categoryRepository = mock(CategoryRepository.class);
        categoryMapper = spy(CategoryMapperImpl.class);
        categoryService = new CategoryService(categoryMapper,categoryRepository);
    }

    @Test
    @Override
    public void createTest() {

    }


    @Test

    @Override
    public void readTest() {

    }

    @Test
    @Override
    public void updateTest() {

    }

    @Test
    @Override
    public void deleteTest() {

    }
}
