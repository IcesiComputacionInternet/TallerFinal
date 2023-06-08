package co.com.icesi.backend.unit.service;

import co.com.icesi.backend.Enum.CategoryType;
import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.request.RequestOrderItemDTO;
import co.com.icesi.backend.mapper.CellphoneMapper;
import co.com.icesi.backend.mapper.OrderMapper;
import co.com.icesi.backend.mapper.OrderMapperImpl;
import co.com.icesi.backend.model.Category;
import co.com.icesi.backend.model.Cellphone;
import co.com.icesi.backend.model.Role;
import co.com.icesi.backend.model.ShopUser;
import co.com.icesi.backend.repository.CellphoneRepository;
import co.com.icesi.backend.repository.OrderRepository;
import co.com.icesi.backend.repository.UserRepository;
import co.com.icesi.backend.service.OrderService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private UserRepository userRepository;
    private CellphoneRepository cellphoneRepository;
    private CellphoneMapper cellphoneMapper;


    private void init(){
        orderRepository = mock(OrderRepository.class);
        orderMapper = spy(OrderMapperImpl.class);
        orderService = new OrderService(userRepository, orderRepository, cellphoneRepository, orderMapper, cellphoneMapper);
        orderService = spy(orderService);
    }

    @Test
    public void testCreateOrder_HappyPath(){
        doNothing().when(orderService).checkPermissionsToCreate();
        when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(defaultUser()));
        when(cellphoneRepository.findById(any())).thenReturn(Optional.ofNullable(defaultItem()));

        orderService.saveOrder(defaultOrder());
    }

    private RequestNewOrderDTO defaultOrder() {
        List<Cellphone> cellphones = new ArrayList<>();
        cellphones.add(defaultItem());
        return RequestNewOrderDTO.builder()
                .total(3789000L)
                .build();
    }

    private Cellphone defaultItem() {
        return Cellphone.builder()
                .cellphoneId(UUID.randomUUID())
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
