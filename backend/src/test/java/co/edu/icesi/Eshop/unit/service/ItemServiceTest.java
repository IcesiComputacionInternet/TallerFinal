package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.mapper.ItemMapper;
import co.edu.icesi.Eshop.repository.ItemRepository;
import co.edu.icesi.Eshop.service.ItemService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class ItemServiceTest {

    private ItemService itemService;

    private ItemRepository itemRepository;

    private ItemMapper itemMapper;

    @BeforeEach
    private void init(){
        itemRepository = mock(ItemRepository.class);
        itemMapper = spy(ItemMapper.class);
        itemService = new ItemService(itemRepository, itemMapper);
    }
}
