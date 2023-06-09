package co.com.icesi.eShop_Back.unit.service;

import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseItemDTO;
import co.com.icesi.eShop_Back.mapper.ItemMapper;
import co.com.icesi.eShop_Back.mapper.ItemMapperImpl;
import co.com.icesi.eShop_Back.model.Category;
import co.com.icesi.eShop_Back.model.Item;
import co.com.icesi.eShop_Back.repository.CategoryRepository;
import co.com.icesi.eShop_Back.repository.ItemRepository;
import co.com.icesi.eShop_Back.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    private ItemService itemService;
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private ItemMapper itemMapper;

    @BeforeEach
    public void setUp(){
        itemRepository = mock(ItemRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        itemMapper = spy(ItemMapperImpl.class);
        itemService = new ItemService(itemRepository, categoryRepository, itemMapper);
    }

    @Test
    public void createTest(){
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.ofNullable(defaultCategory()));
        itemService.create(defaultRequestItemDTO());
        verify(itemMapper, times(1)).fromItemDTO(any());
        verify(itemRepository, times(1)).save(any());
    }

    @Test
    public void createTest_categoryNotFound(){
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());
        try {
            itemService.create(defaultRequestItemDTO());
        } catch (Exception e) {
            assert e.getMessage().equals("Category not found");
        }
        verify(itemMapper, times(0)).fromItemDTO(any());
        verify(itemRepository, times(0)).save(any());
    }

    @Test
    public void deleteTest(){
        itemService.delete(UUID.randomUUID());
        verify(itemRepository, times(1)).deleteById(any());
    }

    @Test
    public void updateTest(){
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.ofNullable(defaultCategory()));
        itemService.update(defaultRequestItemDTO(), UUID.randomUUID());
        verify(itemMapper, times(1)).fromItemDTO(any());
        verify(itemRepository, times(1)).save(any());
    }

    @Test
    public void updateTest_categoryNotFound(){
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());
        try {
            itemService.update(defaultRequestItemDTO(), UUID.randomUUID());
        } catch (Exception e) {
            assert e.getMessage().equals("Category not found");
        }
        verify(itemMapper, times(0)).fromItemDTO(any());
        verify(itemRepository, times(0)).save(any());
    }

    @Test
    public void getTest() {
        UUID itemId = UUID.randomUUID();
        when(itemRepository.findById(itemId)).thenReturn(Optional.ofNullable(defaultItem()));

        ResponseItemDTO responseItem = itemService.get(itemId);

        assertNotNull(responseItem);
        assertEquals("MacBook Pro", responseItem.getName());
        assertEquals("description", responseItem.getDescription());
        assertEquals(1000L, responseItem.getPrice());
        assertEquals("image", responseItem.getImageUrl());
        assertEquals("category", responseItem.getCategory());

        verify(itemRepository, times(1)).findById(itemId);
        verify(itemMapper, times(1)).fromItem(any());
    }

    @Test
    public void getTest_itemNotFound() {
        UUID itemId = UUID.randomUUID();
        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        try {
            itemService.get(itemId);
        } catch (Exception e) {
            assert e.getMessage().equals("Item not found");
        }

        verify(itemRepository, times(1)).findById(itemId);
        verify(itemMapper, times(0)).fromItem(any());
    }

    @Test
    public void getAllTest() {
        List<Item> itemList = Arrays.asList(defaultItem(), defaultItem());
        when(itemRepository.findAll()).thenReturn(itemList);

        List<ResponseItemDTO> responseItemList = itemService.getAll();

        assertNotNull(responseItemList);
        assertEquals(2, responseItemList.size());

        responseItemList.forEach(responseItem -> {
            assertEquals("MacBook Pro", responseItem.getName());
            assertEquals("description", responseItem.getDescription());
            assertEquals(1000L, responseItem.getPrice());
            assertEquals("image", responseItem.getImageUrl());
            assertEquals("category", responseItem.getCategory());
        });

        verify(itemRepository, times(1)).findAll();
        verify(itemMapper, times(2)).fromItem(any());
    }

    private RequestItemDTO defaultRequestItemDTO(){
        return RequestItemDTO.builder()
                .name("MacBook Pro")
                .description("description")
                .price(1000L)
                .imageUrl("image")
                .category("category")
                .build();
    }

    private Item defaultItem(){
        return Item.builder()
                .name("MacBook Pro")
                .description("description")
                .price(1000L)
                .imageUrl("image")
                .category(defaultCategory())
                .build();
    }

    private Category defaultCategory(){
        return Category.builder()
                .name("category")
                .build();
    }


}
