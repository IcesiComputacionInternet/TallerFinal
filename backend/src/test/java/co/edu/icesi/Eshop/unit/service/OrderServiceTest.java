package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.dto.ChangeStatusDTO;
import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.error.exception.EShopException;
import co.edu.icesi.Eshop.mapper.OrderMapper;
import co.edu.icesi.Eshop.mapper.OrderMapperImpl;
import co.edu.icesi.Eshop.model.*;
import co.edu.icesi.Eshop.repository.ItemRepository;
import co.edu.icesi.Eshop.repository.OrderRepository;
import co.edu.icesi.Eshop.repository.UserRepository;
import co.edu.icesi.Eshop.service.OrderService;
import co.edu.icesi.Eshop.unit.matcher.OrderMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class OrderServiceTest {

    private OrderService orderService;

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    private UserRepository userRepository;
    private ItemRepository itemRepository;


    @BeforeEach
    private void init(){
        orderRepository=mock(OrderRepository.class);
        orderMapper=spy(OrderMapperImpl.class);
        userRepository=mock(UserRepository.class);
        itemRepository=mock(ItemRepository.class);
        orderService=new OrderService(orderRepository,orderMapper,userRepository,itemRepository);
        orderService=spy(orderService);

    }

    @Test
    public void testCreateOrder(){
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(defaultUser()));
        when(itemRepository.findByName(any())).thenReturn(Optional.of(defaultItem()));

        orderService.save(defaultOrderDTO());
        EShopOrder order= defaultOrder();

        verify(orderMapper,times(1)).fromOrderDTO(any());
        verify(orderRepository,times(1)).save(argThat(new OrderMatcher(order)));
        verify(orderMapper,times(1)).fromOrder(any());
    }


    @Test
    public void testCreateOrderWhenUserDoesNotExist(){

        try {
            orderService.save(defaultOrderDTO());
            fail();
        }catch(EShopException exception){
            String message= exception.getMessage();
            assertEquals("User does not exists",message);
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);
            assertEquals("ERR_404", detail.getErrorCode(), "Code doesn't match");
            assertEquals("User with email julietav@example.com not found", detail.getErrorMessage(), "Error message doesn't match");


            verify(orderMapper,never()).fromOrderDTO(any());
            verify(orderRepository,never()).findById(any());
            verify(orderRepository,never()).save(any());
            verify(orderMapper,never()).fromOrder(any());
        }
    }

    @Test
    public void testChangeStatusOrder() {

        when(orderRepository.findById(any())).thenReturn(Optional.of(defaultOrder()));

        orderService.changeStatus(statusDTO());

        verify(orderRepository,times(1)).findById(defaultOrder().getOrderId());
        verify(orderRepository,times(1)).save(any());
        verify(orderMapper,times(1)).fromOrder(any());

    }

    @Test
    public void testChangeStatusOrderWhenOrderNotFound() {

        try {
            orderService.changeStatus(statusDTO());
            fail();
        }catch(EShopException exception){
            String message= exception.getMessage();
            assertEquals("Order does not exists",message);
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);
            assertEquals("ERR_404", detail.getErrorCode(), "Code doesn't match");
            assertEquals("Order  a3411d0a-1350-4108-84e2-d13916faf08f not found", detail.getErrorMessage(), "Error message doesn't match");


            verify(orderRepository,times(1)).findById(any());
            verify(orderRepository,never()).save(any());
            verify(orderMapper,never()).fromOrder(any());

        }

    }

    private EShopUser defaultUser(){

        return EShopUser.builder()
                .firstName("Julieta")
                .lastName("Venegas")
                .email("julietav@example.com")
                .phoneNumber("3184441232")
                .password("julieta123")
                .role(defaultRole())
                .birthday(LocalDate.parse("2023-03-27", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .address("955 Steele Street")
                .userId(UUID.randomUUID())
                .build();
    }

    private Role defaultRole(){
        return Role.builder()
                .roleName(Roles.ADMIN.toString())
                .description("is an administrator")
                .roleId(UUID.randomUUID())
                .build();
    }

    private EShopOrder defaultOrder(){
        return EShopOrder.builder()
                .orderId(UUID.fromString("a3411d0a-1350-4108-84e2-d13916faf08f"))
                .status(Status.PENDING.toString())
                .total(1000)
                .eShopUser(defaultUser())
                .items(Stream.of(defaultItem()).collect(Collectors.toList()))
                .build();
    }

    private OrderDTO defaultOrderDTO(){
        return OrderDTO.builder()
                .status(Status.PENDING.toString())
                .total(1000)
                .userEmail("julietav@example.com")
                .userPhoneNumber("3184441232")
                .items(Stream.of("Licuadora 200X").collect(Collectors.toList()))
                .build();
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
    private ChangeStatusDTO statusDTO(){
        return ChangeStatusDTO.builder()
                .orderId("a3411d0a-1350-4108-84e2-d13916faf08f")
                .status(Status.RECEIVED.toString())
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
