package co.com.icesi.eShop_Back.unit.service;

import co.com.icesi.eShop_Back.dto.request.RequestCategoryDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseCategoryDTO;
import co.com.icesi.eShop_Back.error.exception.CustomException;
import co.com.icesi.eShop_Back.mapper.CategoryMapper;
import co.com.icesi.eShop_Back.mapper.CategoryMapperImpl;
import co.com.icesi.eShop_Back.mapper.ItemMapper;
import co.com.icesi.eShop_Back.mapper.ItemMapperImpl;
import co.com.icesi.eShop_Back.model.Category;
import co.com.icesi.eShop_Back.model.Item;
import co.com.icesi.eShop_Back.repository.CategoryRepository;
import co.com.icesi.eShop_Back.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private ItemMapper itemMapper;

    @BeforeEach
    public void init() {
        categoryRepository = mock(CategoryRepository.class);
        categoryMapper = spy(CategoryMapperImpl.class);
        itemMapper = spy(ItemMapperImpl.class);
        categoryService = new CategoryService(categoryRepository, categoryMapper, itemMapper);
    }

    @Test
    public void testCreate() {
        when(categoryRepository.existsByName("Laptop")).thenReturn(false);
        categoryService.create(defaultRequestCategoryDTO());
        verify(categoryRepository, times(1)).existsByName(any());
        verify(categoryMapper, times(1)).fromCategoryDTO(any());
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    public void testCreateWhenCategoryAlreadyExists() {
        when(categoryRepository.existsByName(any())).thenReturn(true);

        try {
            categoryService.create(defaultRequestCategoryDTO());
        } catch (CustomException e) {
            assertEquals("Category already exists", e.getMessage());
            verify(categoryRepository, times(1)).existsByName(any());
            verify(categoryMapper, times(0)).fromCategoryDTO(any());
            verify(categoryRepository, times(0)).save(any());
        }
    }

    @Test
    public void testDelete() {
        categoryService.delete(any());
        verify(categoryRepository, times(1)).deleteById(any());
    }

    @Test
    public void testUpdate() {
        categoryService.update(defaultRequestCategoryDTO(), any());
        verify(categoryMapper, times(1)).fromCategoryDTO(any());
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    public void testGet() {
        when(categoryRepository.findById(any())).thenReturn(Optional.ofNullable(defaultCategory()));
        categoryService.get(UUID.fromString("f7a2d6c2-4b2c-4b9a-9f4d-1b1f7b1f1b1f"));
        verify(categoryRepository, times(1)).findById(any());
        verify(categoryMapper, times(1)).fromCategory(any());
        verify(itemMapper, times(2)).fromItem(any());
    }

    @Test
    public void testGetWhenCategoryDoesNotExist() {
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        try {
            categoryService.get(any());
        } catch (CustomException e) {
            assertEquals("Category not found", e.getMessage());
            verify(categoryRepository, times(1)).findById(any());
            verify(categoryMapper, times(0)).fromCategory(any());
            verify(itemMapper, times(0)).fromItem(any());
        }
    }

    @Test
    public void testGetAll() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(defaultCategory(), defaultCategory()));
        var list = categoryService.getAll();
        assertEquals(2, list.size());
        verify(categoryRepository, times(1)).findAll();
        verify(categoryMapper, times(2)).fromCategory(any());
        verify(itemMapper, times(4)).fromItem(any());
    }

    private RequestCategoryDTO defaultRequestCategoryDTO() {
        return RequestCategoryDTO.builder()
                .name("Laptop")
                .description("test")
                .build();
    }

    private Category defaultCategory() {
        return Category.builder()
                .categoryId(UUID.fromString("f7a2d6c2-4b2c-4b9a-9f4d-1b1f7b1f1b1f"))
                .name("Laptop")
                .description("test")
                .items(defaultItems())
                .build();
    }

    private List<Item> defaultItems() {
        return Arrays.asList(
                Item.builder()
                        .itemId(UUID.randomUUID())
                        .name("Laptop")
                        .description("test")
                        .price(1000L)
                        .category(null)
                        .build(),
                Item.builder()
                        .itemId(UUID.randomUUID())
                        .name("Laptop")
                        .description("test")
                        .price(1000L)
                        .category(null            )
                        .build()
        );
    }


}
