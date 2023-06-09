package co.com.icesi.backend.unit.service;

import co.com.icesi.backend.Enum.CategoryType;
import co.com.icesi.backend.Enum.OrderStatus;
import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.RequestChangeOrderDTO;
import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.request.RequestOrderItemDTO;
import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import co.com.icesi.backend.mapper.CellphoneMapper;
import co.com.icesi.backend.mapper.OrderMapper;
import co.com.icesi.backend.mapper.OrderMapperImpl;
import co.com.icesi.backend.model.*;
import co.com.icesi.backend.repository.CellphoneRepository;
import co.com.icesi.backend.repository.OrderRepository;
import co.com.icesi.backend.repository.UserRepository;
import co.com.icesi.backend.security.CellphoneSecurityContext;
import co.com.icesi.backend.service.OrderService;
import co.com.icesi.backend.unit.service.matcher.OrderMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private UserRepository userRepository;
    private CellphoneRepository cellphoneRepository;
    private CellphoneMapper cellphoneMapper;


    @BeforeEach
    private void init(){
        orderRepository = mock(OrderRepository.class);
        userRepository = mock(UserRepository.class);
        cellphoneRepository = mock(CellphoneRepository.class);
        orderMapper = spy(OrderMapperImpl.class);
        orderService = new OrderService(userRepository, orderRepository, cellphoneRepository, orderMapper, cellphoneMapper);
        orderService = spy(orderService);
    }

    @Test
    public void testCreateOrder_HappyPath(){
        doNothing().when(orderService).checkPermissionsToCreate();
        when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(defaultUser()));
        when(cellphoneRepository.findById(any())).thenReturn(Optional.ofNullable(defaultItem()));

        ResponseOrderDTO responseOrderDTO = orderService.saveOrder(defaultOrderDTO());
        ShopOrder order = defaultOrder();

        assertEquals("Order successfully created", responseOrderDTO.getMessage());
        verify(orderRepository, times(1)).save(argThat(new OrderMatcher(order)));
        verify(userRepository, times(1)).findByEmail(any());
        verify(orderMapper, times(1)).fromRequestOrderDTO(any());
        verify(orderMapper, times(1)).fromOrderToResponseOrderDTO(any(), any());
    }

    @Test
    public void testChangeOrderStatus_HappyPath(){
        doNothing().when(orderService).checkPermissionToChangeStatus();
        when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(defaultOrder()));

        ResponseOrderDTO responseOrderDTO = orderService.changeOrderStatus(defaultChangeOrderDTO());

        assertEquals(OrderStatus.DELIVERED.getStatus(), responseOrderDTO.getStatus());
        verify(orderRepository, times(1)).findById(any());
        verify(orderRepository, times(1)).save(any());
        verify(orderMapper, times(1)).fromRequestChangeToResponseOrderDTO(any(), any());
    }

    private RequestChangeOrderDTO defaultChangeOrderDTO(){
        return RequestChangeOrderDTO.builder()
                .orderId(UUID.fromString("de7ae704-6bbf-47da-925a-b0bdd13351cf"))
                .newStatus(OrderStatus.DELIVERED.getStatus())
                .build();
    }

    private ShopOrder defaultOrder() {
        List<Cellphone> items = new ArrayList<>();
        Set<Integer> quantities = new HashSet<>();
        items.add(defaultItem());
        return ShopOrder.builder()
                .orderId(UUID.randomUUID())
                .shopUser(defaultUser())
                .status(OrderStatus.IN_PROCESS)
                .total(3789000L)
                .items(items)
                .quantities(quantities)
                .build();
    }

    private RequestNewOrderDTO defaultOrderDTO() {
        List<RequestOrderItemDTO> cellphones = new ArrayList<>();
        cellphones.add(defaultOrderItemDTO());
        return RequestNewOrderDTO.builder()
                .total(3789000L)
                .items(cellphones)
                .build();
    }

    private Cellphone defaultItem() {
        return Cellphone.builder()
                .cellphoneId(UUID.fromString("de7ae704-6bbf-47da-925a-b0bdd13351cf"))
                .name("Apple iPhone 13 128 GB")
                .description("iPhone 13. Tu nuevo superpoder. ¿Cómo hicimos para ponerle cámaras " +
                        "tan poderosas? Pensando en diagonal Diseñamos una arquitectura " +
                        "completamente nueva y giramos los lentes 45 grados. ")
                .price(3789000L)
                .imageUrl("https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSxr5PJm5AS9C2BsmZlid_6nF8IYQ4XJZSDRMK-dv-hq7veiHlYDv2RKvdi4iooJRE8ZyRzRq4rIDD4pKfab68faIx9Acj9xMy-Qr7f6q0")
                .brand("Apple")
                .storage("128Gb")
                .ram("4")
                .operatingSystem("iOS 15")
                .frontCameraResolution("Single 13 MP")
                .mainCameraResolution("TrueDepth de 12 MP con modo Noche y grabación de video 4K HDR en Dolby Vision")
                .screenSize("Pantalla Super Retina XDR de 6.1 pulgadas")
                .stock(20)
                .category(defaultCategory())
                .build();
    }

    private RequestOrderItemDTO defaultOrderItemDTO(){
        return RequestOrderItemDTO.builder()
                .id(UUID.fromString("de7ae704-6bbf-47da-925a-b0bdd13351cf"))
                .quantity(5)
                .build();
    }

    private Category defaultCategory(){
        return Category.builder()
                .categoryId(UUID.randomUUID())
                .name(CategoryType.HIGH_RANGE.getCategory())
                .description("Category description")
                .build();
    }

    private ShopUser defaultUser() {
        return ShopUser.builder()
                .userId(UUID.randomUUID())
                .firstName("Luis Miguel")
                .lastName("Ossa Arias")
                .email("luismiguel@gmail.com")
                .password("password")
                .phoneNumber("+573175933339")
                .address("Cra 7 #69-64")
                .birthday(LocalDateTime.of(2002, 7, 5, 0, 0))
                .role(defaultRole())
                .build();
    }

    private Role defaultRole(){
        return Role.builder()
                .roleId(UUID.randomUUID())
                .roleName(UserRole.USER.getRole())
                .description("User role")
                .build();
    }
}
