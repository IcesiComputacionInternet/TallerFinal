package co.com.icesi.Eshop.Unit.service;

import co.com.icesi.Eshop.Unit.util.CrudTest;
import co.com.icesi.Eshop.mapper.ItemMapper;
import co.com.icesi.Eshop.mapper.ItemMapperImpl;
import co.com.icesi.Eshop.repository.CategoryRepository;
import co.com.icesi.Eshop.repository.ItemRepository;
import co.com.icesi.Eshop.repository.OrderRepository;
import co.com.icesi.Eshop.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class ItemServiceTest  implements CrudTest {

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private ItemMapper itemMapper;

    private OrderRepository orderRepository;

    private ItemService itemService;

    @BeforeEach
    public void init(){
        itemRepository = mock(ItemRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        itemMapper = spy(ItemMapperImpl.class);
        orderRepository = mock(OrderRepository.class);
        itemService = new ItemService(itemRepository, orderRepository, categoryRepository, itemMapper);


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
