package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.error.exception.EShopException;
import co.edu.icesi.Eshop.mapper.ItemMapper;
import co.edu.icesi.Eshop.mapper.ItemMapperImpl;
import co.edu.icesi.Eshop.model.Category;
import co.edu.icesi.Eshop.model.Item;
import co.edu.icesi.Eshop.repository.CategoryRepository;
import co.edu.icesi.Eshop.repository.ItemRepository;
import co.edu.icesi.Eshop.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    private ItemService itemService;

    private ItemRepository itemRepository;

    private CategoryRepository categoryRepository;

    private ItemMapper itemMapper;

    @BeforeEach
    private void init(){
        itemRepository = mock(ItemRepository.class);
        itemMapper = spy(ItemMapperImpl.class);
        categoryRepository = mock(CategoryRepository.class);
        itemService = new ItemService(itemRepository, categoryRepository, itemMapper);
    }

    @Test
    @DisplayName("Item created")
    public void testCreateItem(){
        var item = defaultItem();
        when(itemRepository.findByName(any())).thenReturn(Optional.empty());
        when(categoryRepository.findByName(defaultCategory().getName())).thenReturn(Optional.of(defaultCategory()));

        itemService.save(itemMapper.fromItem(item));

        verify(itemRepository, times(1)).save(any());
        verify(itemMapper, times(1)).fromItemDTO(any());
    }

    @Test
    @DisplayName("Item not created. Duplicated name")
    public void testCreateItemWithNameThatAlreadyExists(){
        var item = defaultItem();
        when(itemRepository.findByName(item.getName())).thenReturn(Optional.of(item));

        var exception = assertThrows(EShopException.class, () -> itemService.save(itemMapper.fromItem(item)), "No exception was thrown");

        var error = exception.getError();
        var details = error.getDetails();
        assertEquals(1, details.size());
        var detail = details.get(0);
        assertEquals("ERR_DUPLICATED", detail.getErrorCode(), "Code doesn't match");
        assertEquals("Item with name Licuadora 200X already exists", detail.getErrorMessage(), "Error message doesn't match");
    }

    @Test
    @DisplayName("Item state changed")
    public void testSetItemState(){
        var item = defaultItem();
        when(categoryRepository.findByName(defaultCategory().getName())).thenReturn(Optional.of(defaultCategory()));
        when(itemRepository.findByName(item.getName())).thenReturn(Optional.of(item));

        itemService.setItemState(item.getName());

        verify(itemRepository, times(1)).save(any());

        assertFalse(itemRepository.findByName(item.getName()).get().isAvailable());
    }

    private Item defaultItem(){
        return Item.builder()
                .itemId(UUID.fromString("6b5b09b8-55b9-4186-99cf-adae13957426"))
                .name("Licuadora 200X")
                .description("Licuadora modelo 200X")
                .category(defaultCategory())
                .imageUrl("")
                .price(250000L)
                .minVoltage(1.2)
                .maxVoltage(1.7)
                .sourceOfEnergy("Energía por cable")
                .levelOfEfficiency("A")
                .marca("IMUSA")
                .model("200X")
                .guarantee(24)
                .available(true)
                .build();
    }

    private Category defaultCategory(){
        return Category.builder()
                .categoryId(UUID.fromString("d575d4a8-9897-431e-99a7-912a8842ecc5"))
                .name("Preparación de alimentos")
                .description("Categoria con electrodomesticos para hacer comida")
                .build();
    }
}
