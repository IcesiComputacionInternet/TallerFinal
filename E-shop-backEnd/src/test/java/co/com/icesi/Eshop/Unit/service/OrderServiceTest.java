package co.com.icesi.Eshop.Unit.service;

import co.com.icesi.Eshop.Unit.util.CrudTest;
import co.com.icesi.Eshop.mapper.OrderMapper;
import co.com.icesi.Eshop.mapper.OrderMapperImpl;
import co.com.icesi.Eshop.repository.ItemRepository;
import co.com.icesi.Eshop.repository.OrderRepository;
import co.com.icesi.Eshop.repository.UserRepository;
import co.com.icesi.Eshop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class OrderServiceTest implements CrudTest {
    private OrderService orderService;
    private  OrderRepository orderRepository;
    private  OrderMapper orderMapper;
    private  ItemRepository itemRepository;
    private  UserRepository userRepository;

    @BeforeEach
    public void init(){
        orderRepository = mock(OrderRepository.class);
        orderMapper = spy(OrderMapperImpl.class);
        itemRepository = mock(ItemRepository.class);
        userRepository = mock(UserRepository.class);
        orderService = new OrderService(orderRepository,orderMapper,itemRepository,userRepository);
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
