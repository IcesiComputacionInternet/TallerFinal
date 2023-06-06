package co.com.icesi.Eshop.Unit.service;

import co.com.icesi.Eshop.Unit.util.CrudTest;
import co.com.icesi.Eshop.Unit.util.Matcher.OrderMatcher;
import co.com.icesi.Eshop.dto.request.OrderDTO;
import co.com.icesi.Eshop.dto.response.OrderResponseDTO;
import co.com.icesi.Eshop.mapper.OrderMapper;
import co.com.icesi.Eshop.mapper.OrderMapperImpl;
import co.com.icesi.Eshop.model.Item;
import co.com.icesi.Eshop.model.OrderStore;
import co.com.icesi.Eshop.model.UserPrincipal;
import co.com.icesi.Eshop.repository.ItemRepository;
import co.com.icesi.Eshop.repository.OrderRepository;
import co.com.icesi.Eshop.repository.UserRepository;
import co.com.icesi.Eshop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserEmail("test@example.com");
        orderDTO.setItems(Collections.singletonList("item1"));

        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setEmail(orderDTO.getUserEmail());

        Item item1 = new Item();
        item1.setName("item1");

        OrderStore order = new OrderStore();
        order.setOrderId(UUID.randomUUID());

        OrderResponseDTO expectedResponse = new OrderResponseDTO();
        expectedResponse.setId(String.valueOf(order.getOrderId()));

        when(userRepository.findByEmail(orderDTO.getUserEmail())).thenReturn(Optional.of(userPrincipal));
        when(itemRepository.findByName("item1")).thenReturn(Optional.of(item1));
        when(orderMapper.toOrder(orderDTO)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toOrderResponseDTO(order)).thenReturn(expectedResponse);

        // Act
        OrderResponseDTO result = orderService.createOrder(orderDTO);

        // Assert
        verify(userRepository).findByEmail(orderDTO.getUserEmail());
        verify(itemRepository).findByName("item1");
        verify(orderMapper).toOrder(orderDTO);
        verify(orderRepository).save(argThat(new OrderMatcher(order)));
        verify(orderMapper).toOrderResponseDTO(order);
        assertNotNull(result);
    }

    @Test
    public void testCreateOrder_UserNotFound() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserEmail("test@example.com");

        when(userRepository.findByEmail(orderDTO.getUserEmail())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> orderService.createOrder(orderDTO));
        verify(userRepository).findByEmail(orderDTO.getUserEmail());
        verify(itemRepository, never()).findByName(anyString());
        verify(orderMapper, never()).toOrder(any(OrderDTO.class));
        verify(orderRepository, never()).save(any());
        verify(orderMapper, never()).toOrderResponseDTO(any(OrderStore.class));
    }

    @Test
    public void testCreateOrder_ItemNotFound() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserEmail("test@example.com");
        orderDTO.setItems(Collections.singletonList("item1"));

        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setEmail(orderDTO.getUserEmail());

        when(userRepository.findByEmail(orderDTO.getUserEmail())).thenReturn(Optional.of(userPrincipal));
        when(itemRepository.findByName("item1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> orderService.createOrder(orderDTO));
        verify(userRepository).findByEmail(orderDTO.getUserEmail());
        verify(itemRepository).findByName("item1");
        verify(orderMapper, never()).toOrder(any(OrderDTO.class));
        verify(orderRepository, never()).save(any());
        verify(orderMapper, never()).toOrderResponseDTO(any(OrderStore.class));


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
