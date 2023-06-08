package co.com.icesi.eShopBackEnd.unit.service;

import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import co.com.icesi.eShopBackEnd.mapper.ItemMapper;
import co.com.icesi.eShopBackEnd.mapper.ItemMapperImpl;
import co.com.icesi.eShopBackEnd.model.Category;
import co.com.icesi.eShopBackEnd.model.Customer;
import co.com.icesi.eShopBackEnd.model.Item;
import co.com.icesi.eShopBackEnd.repository.CategoryRepository;
import co.com.icesi.eShopBackEnd.repository.ItemRepository;
import co.com.icesi.eShopBackEnd.service.CustomerService;
import co.com.icesi.eShopBackEnd.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

public class ItemServiceTest {
    private ItemService itemService;
    private  ItemRepository itemRepository;
    private  CategoryRepository categoryRepository;
    private  ItemMapper itemMapper;
    @BeforeEach
    public void init() {
        itemRepository = mock(ItemRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        itemMapper = spy(ItemMapperImpl.class);
        itemService = new ItemService(itemRepository,categoryRepository,itemMapper);
    }
    @Test
    public void testCreateItemWhenNotExistCategory() {
        try{
        itemService.save(defaultItemDto());
        } catch (RuntimeException exception) {
            assertEquals("Not existing data", exception.getMessage());


        }
    }
    @Test
    public void testCreateItemWhenAlreadyExist() {
        try{
            when(itemRepository.itemExists(any())).thenReturn(true);
            itemService.save(defaultItemDto());
        } catch (RuntimeException exception) {
            assertEquals("Existing data", exception.getMessage());


        }
    }
    @Test
    public void testCreateItem() {
            when(itemRepository.itemExists(any())).thenReturn(false);
            when(categoryRepository.returnCategory(any())).thenReturn(Optional.of(defaulCategory()));
            when(itemRepository.save(any())).thenReturn(defaultItem());
            when(itemMapper.fromItemToResponseItemDTO(any())).thenReturn(defaulResponseItemDTO());
            itemService.save(defaultItemDto());
            assertEquals("TV", defaulResponseItemDTO().getName());
            assertEquals(2000L, defaulResponseItemDTO().getPrice());
            verify(itemRepository, times(1)).save(any());
    }

    private Item defaultItem() {
        return Item.builder().name("TV").price(2000L).category(defaulCategory()).brand("password").stock(3).imageURL("tv.com").description("TV").build();
    }

    private CreateItemDTO defaultItemDto() {
        return CreateItemDTO.builder().name("TV").price(2000L).category("TV").brand("password").stock(3).imageURL("tv.com").description("TV").build();
    }

    private Category defaulCategory() {
        return Category.builder().name("TV").description("TV").build();
    }
    private CreateCategoryDTO defaulCreateCategoryDTO() {
        return CreateCategoryDTO.builder().name("TV").description("TV").build();
    }
    private ResponseItemDTO defaulResponseItemDTO() {
        return ResponseItemDTO.builder().name("TV").price(2000L).category(defaulCreateCategoryDTO()).brand("password").stock(3).imageURL("tv.com").description("TV").build();
    }
}
