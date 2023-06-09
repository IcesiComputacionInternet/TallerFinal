package co.com.icesi.eShop_Back.unit.service;

import co.com.icesi.eShop_Back.dto.request.RequestOrderDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseOrderDTO;
import co.com.icesi.eShop_Back.enums.Status;
import co.com.icesi.eShop_Back.error.exception.CustomException;
import co.com.icesi.eShop_Back.mapper.ItemMapper;
import co.com.icesi.eShop_Back.mapper.ItemMapperImpl;
import co.com.icesi.eShop_Back.mapper.OrderMapper;
import co.com.icesi.eShop_Back.mapper.OrderMapperImpl;
import co.com.icesi.eShop_Back.model.Category;
import co.com.icesi.eShop_Back.model.Item;
import co.com.icesi.eShop_Back.model.Order;
import co.com.icesi.eShop_Back.model.Role;
import co.com.icesi.eShop_Back.model.User;
import co.com.icesi.eShop_Back.repository.ItemRepository;
import co.com.icesi.eShop_Back.repository.OrderRepository;
import co.com.icesi.eShop_Back.repository.UserRepository;
import co.com.icesi.eShop_Back.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private OrderMapper orderMapper;
    private ItemMapper itemMapper;

    @BeforeEach
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        userRepository = mock(UserRepository.class);
        itemRepository = mock(ItemRepository.class);
        orderMapper = spy(OrderMapperImpl.class);
        itemMapper = spy(ItemMapperImpl.class);
        orderService = new OrderService(orderRepository, userRepository, itemRepository, orderMapper, itemMapper);
    }

    @Test
    public void createTest() {
        RequestOrderDTO requestOrderDTO = defaultRequestOrderDTO();
        User user = defaultUser();
        List<Item> items = Collections.singletonList(defaultItem());

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(user));
        when(itemRepository.findAllById(anyList())).thenReturn(items);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        orderService.create(requestOrderDTO);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository, times(1)).save(orderCaptor.capture());
        Order createdOrder = orderCaptor.getValue();

        assertNotNull(createdOrder);
        assertEquals(user, createdOrder.getUser());
        assertEquals(items, createdOrder.getItems());
        assertEquals(Status.PENDING, createdOrder.getStatus());
    }

    @Test
    public void createTest_userNotFound() {
        RequestOrderDTO requestOrderDTO = defaultRequestOrderDTO();

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> orderService.create(requestOrderDTO));

        verify(orderRepository, times(0)).save(any());
    }

    @Test
    public void deleteTest() {
        UUID orderId = UUID.randomUUID();

        doNothing().when(orderRepository).deleteById(any(UUID.class));

        orderService.delete(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    public void updateStatusTest() {
        UUID orderId = UUID.fromString("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1e");
        Order order = defaultOrder();

        when(orderRepository.findById(any(UUID.class))).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        orderService.updateStatus(orderId, Status.PAID);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository, times(1)).save(orderCaptor.capture());
        Order updatedOrder = orderCaptor.getValue();

        assertNotNull(updatedOrder);
        assertEquals(orderId, updatedOrder.getOrderId());
        assertEquals(Status.PAID, updatedOrder.getStatus());
    }

    @Test
    public void updateStatusTest_orderNotFound() {
        UUID orderId = UUID.randomUUID();

        when(orderRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> orderService.updateStatus(orderId, Status.PAID));

        verify(orderRepository, times(0)).save(any());
    }

    @Test
    public void getTest() {
        UUID orderId = UUID.fromString("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1e");
        Order order = defaultOrder();

        when(orderRepository.findById(any(UUID.class))).thenReturn(Optional.of(order));

        ResponseOrderDTO responseOrderDTO = orderService.get(orderId);

        assertNotNull(responseOrderDTO);
        assertEquals(orderId, responseOrderDTO.getOrderId());
    }

    @Test
    public void getTest_orderNotFound() {
        UUID orderId = UUID.randomUUID();

        when(orderRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> orderService.get(orderId));
    }

    @Test
    public void getAllTest() {
        Order order1 = defaultOrder();
        Order order2 = defaultOrder();

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
        when(orderMapper.orderToOrderDTO(any(Order.class))).thenReturn(defaultResponseOrderDTO());

        List<ResponseOrderDTO> responseOrderDTOs = orderService.getAll();

        assertNotNull(responseOrderDTOs);
        assertEquals(2, responseOrderDTOs.size());
    }

    @Test
    public void getByUserTest() {
        User user = defaultUser();
        Order order = defaultOrder();

        when(orderRepository.findByUserEmail(anyString())).thenReturn(Optional.of(order));
        when(orderMapper.orderToOrderDTO(any(Order.class))).thenReturn(defaultResponseOrderDTO());

        ResponseOrderDTO responseOrderDTO = orderService.getByUser(user.getEmail());

        assertNotNull(responseOrderDTO);
        assertEquals(user.getUserId(), responseOrderDTO.getUser());
    }

    @Test
    public void getByUserTest_orderNotFound() {
        String email = "admin@gmail.com";

        when(orderRepository.findByUserEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> orderService.getByUser(email));
    }

    private RequestOrderDTO defaultRequestOrderDTO() {
        return RequestOrderDTO.builder()
                .user("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1b")
                .total(1000L)
                .items(List.of("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1c", "f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1c"))
                .build();
    }

    private User defaultUser() {
        return User.builder()
                .userId(UUID.fromString("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1b"))
                .firstName("firstName")
                .lastName("lastName")
                .email("admin@gmail.com")
                .password("1234")
                .role(defaultRole())
                .build();
    }

    private Role defaultRole() {
        return Role.builder()
                .roleId(UUID.fromString("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1c"))
                .name("ADMIN")
                .build();
    }

    private Item defaultItem() {
        return Item.builder()
                .itemId(UUID.fromString("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1d"))
                .name("MacBook Pro")
                .description("description")
                .price(1000L)
                .imageUrl("image")
                .category(defaultCategory())
                .build();
    }

    private Category defaultCategory() {
        return Category.builder()
                .name("category")
                .build();
    }

    private Order defaultOrder() {
        Order order = new Order();
        order.setOrderId(UUID.fromString("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1e"));
        order.setUser(defaultUser());
        order.setItems(Collections.singletonList(defaultItem()));
        order.setStatus(Status.PENDING);
        return order;
    }

    private ResponseOrderDTO defaultResponseOrderDTO() {
        return ResponseOrderDTO.builder()
                .orderId(UUID.randomUUID())
                .user(UUID.fromString("f7c2c8a0-2b7a-4e1a-8f1a-3b9b4b1b1b1b"))
                .status(Status.PENDING)
                .build();
    }
}
