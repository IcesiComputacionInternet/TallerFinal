package co.com.icesi.eShopBackEnd.unit.service;


import co.com.icesi.eShopBackEnd.dto.AssignCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import co.com.icesi.eShopBackEnd.mapper.CategoryMapper;
import co.com.icesi.eShopBackEnd.mapper.ItemMapper;
import co.com.icesi.eShopBackEnd.mapper.CategoryMapperImpl;
import co.com.icesi.eShopBackEnd.mapper.ItemMapperImpl;

import co.com.icesi.eShopBackEnd.model.Category;
import co.com.icesi.eShopBackEnd.model.Item;
import co.com.icesi.eShopBackEnd.repository.CategoryRepository;
import co.com.icesi.eShopBackEnd.repository.ItemRepository;
import co.com.icesi.eShopBackEnd.service.CategoryService;
import co.com.icesi.eShopBackEnd.unit.matcher.CategoryMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CategoryServiceTest {
    private  CategoryRepository categoryRepository;
    private  ItemRepository itemRepository;
    private  CategoryMapper categoryMapper;
    private  ItemMapper itemMapper;
    private CategoryService categoryService;
    @BeforeEach
    public void init(){
        categoryRepository = mock(CategoryRepository.class);
        itemRepository = mock(ItemRepository.class);
        categoryService = mock(CategoryService.class);
        categoryMapper = spy(CategoryMapperImpl.class);
        itemMapper = spy(ItemMapperImpl.class);
        categoryService = new CategoryService(categoryRepository, itemRepository,categoryMapper,itemMapper);
    }
    @Test
    public void testCreateCategory(){
        categoryService.save(categoryDTO());
        Category category = Category.builder()
                .name("Teacher")
                .description("Is a teacher the university Icesi")
                .build();
        verify(categoryRepository, times(1)).save(argThat(new CategoryMatcher(category)));
    }

    @Test
    public void testCreateRoleWithExistingName(){
        when(categoryRepository.categoryExists(any())).thenReturn(true);
        try{
            categoryService.save(categoryDTO());
        }catch (RuntimeException exception){
            assertEquals("Existing data", exception.getMessage());
        }
    }
    @Test
    public void testAssignCategoryNoExistCategory(){
        try{
            categoryService.assignCategory(assignCategoryNoExistDTO());
        }catch (RuntimeException exception) {
            assertEquals("Category not found", exception.getMessage());
        }

    }
    @Test
    public void testAssignCategoryButItemNoFound(){
        categoryService.save(categoryDTO());
        Category category = category();
        try {
            when(categoryRepository.returnCategory(any())).thenReturn(Optional.of(category));
            categoryService.assignCategory(AssignCategoryDTO.builder()
                    .itemName("Teacher")
                    .category("Teacher")
                    .build());
        }catch (RuntimeException exception) {
            assertEquals("Item not found", exception.getMessage());
        }

    }
    @Test
    public void testAssignCategory(){
        categoryService.save(categoryDTO());
        Category category = category();
        Item item = item();
        when(categoryRepository.returnCategory(any())).thenReturn(Optional.of(category));
        when(itemRepository.returnItem(any())).thenReturn(Optional.of(item));
        categoryService.assignCategory(AssignCategoryDTO.builder()
                .itemName("item")
                .category("Teacher")
                .build());

    }

    private Item item() {
        return Item.builder()
                .name("item")
                .description("Is a teacher the university Icesi")
                .build();
    }




    private Category category() {
        return Category.builder()
                .name("Teacher")
                .description("Is a teacher the university Icesi")
                .build();
    }



    public CreateCategoryDTO categoryDTO(){
        return CreateCategoryDTO.builder()
                .name("Teacher")
                .description("Is a teacher the university Icesi")
                .build();
    }



    public AssignCategoryDTO assignCategoryNoExistDTO(){
        return AssignCategoryDTO.builder()
                .itemName("Teacher")
                .category("No exist")
                .build();
    }



}