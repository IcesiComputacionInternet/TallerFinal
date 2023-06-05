package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.error.exception.EShopException;
import co.edu.icesi.Eshop.mapper.ItemMapper;
import co.edu.icesi.Eshop.mapper.ItemMapperImpl;
import co.edu.icesi.Eshop.model.Category;
import co.edu.icesi.Eshop.model.Item;
import co.edu.icesi.Eshop.repository.ItemRepository;
import co.edu.icesi.Eshop.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    private ItemService itemService;

    private ItemRepository itemRepository;

    private ItemMapper itemMapper;

    @BeforeEach
    private void init(){
        itemRepository = mock(ItemRepository.class);
        itemMapper = spy(ItemMapperImpl.class);
        itemService = new ItemService(itemRepository, itemMapper);
    }

    @Test
    @DisplayName("Item created")
    public void testCreateItem(){
        var item = defaultItem();
        when(itemRepository.findByName(any())).thenReturn(Optional.empty());

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
    @DisplayName("Item not created. Invalid price")
    public void testCreateItemWithPriceZero(){
        var item = defaultItem();
        item.setPrice(0L);
        when(itemRepository.findByName(any())).thenReturn(Optional.empty());

        var exception = assertThrows(EShopException.class, () -> itemService.save(itemMapper.fromItem(item)), "No exception was thrown");

        var error = exception.getError();
        var details = error.getDetails();
        assertEquals(1, details.size());
        var detail = details.get(0);
        assertEquals("ERR_400", detail.getErrorCode(), "Code doesn't match");
        assertEquals("Invalid price for Item. Can't be "+item.getPrice(), detail.getErrorMessage(), "Error message doesn't match");
    }

    @Test
    @DisplayName("Item not created. Invalid minimum voltage")
    public void testCreateItemWithMinimumVoltageLessThanZero(){
        var item = defaultItem();
        item.setMinVoltage(-1.2);
        when(itemRepository.findByName(any())).thenReturn(Optional.empty());

        var exception = assertThrows(EShopException.class, () -> itemService.save(itemMapper.fromItem(item)), "No exception was thrown");

        var error = exception.getError();
        var details = error.getDetails();
        assertEquals(1, details.size());
        var detail = details.get(0);
        assertEquals("ERR_400", detail.getErrorCode(), "Code doesn't match");
        assertEquals("Invalid voltage for Item. Can't be "+item.getMinVoltage(), detail.getErrorMessage(), "Error message doesn't match");
    }

    @Test
    @DisplayName("Item not created. Invalid maximum voltage")
    public void testCreateItemWithMaximumVoltageZero(){
        var item = defaultItem();
        item.setMaxVoltage(0);
        when(itemRepository.findByName(any())).thenReturn(Optional.empty());

        var exception = assertThrows(EShopException.class, () -> itemService.save(itemMapper.fromItem(item)), "No exception was thrown");

        var error = exception.getError();
        var details = error.getDetails();
        assertEquals(1, details.size());
        var detail = details.get(0);
        assertEquals("ERR_400", detail.getErrorCode(), "Code doesn't match");
        assertEquals("Invalid voltage for Item. Can't be "+item.getMaxVoltage(), detail.getErrorMessage(), "Error message doesn't match");
    }

    @Test
    @DisplayName("Item not created. Invalid guarantee")
    public void testCreateItemWithGuaranteeLessThanZero(){
        var item = defaultItem();
        item.setGuarantee(-5);
        when(itemRepository.findByName(any())).thenReturn(Optional.empty());

        var exception = assertThrows(EShopException.class, () -> itemService.save(itemMapper.fromItem(item)), "No exception was thrown");

        var error = exception.getError();
        var details = error.getDetails();
        assertEquals(1, details.size());
        var detail = details.get(0);
        assertEquals("ERR_400", detail.getErrorCode(), "Code doesn't match");
        assertEquals("Invalid guarantee for Item. Can't be "+item.getGuarantee(), detail.getErrorMessage(), "Error message doesn't match");
    }
    @Test
    @DisplayName("Item not created. Invalid level of efficiency")
    public void testCreateItemWithBadLevelOfEfficiency(){
        var item = defaultItem();
        item.setLevelOfEfficiency("H");
        when(itemRepository.findByName(any())).thenReturn(Optional.empty());

        var exception = assertThrows(EShopException.class, () -> itemService.save(itemMapper.fromItem(item)), "No exception was thrown");

        var error = exception.getError();
        var details = error.getDetails();
        assertEquals(1, details.size());
        var detail = details.get(0);
        assertEquals("ERR_400", detail.getErrorCode(), "Code doesn't match");
        assertEquals("Level of efficiency "+ item.getLevelOfEfficiency() +" invalid", detail.getErrorMessage(), "Error message doesn't match");
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
