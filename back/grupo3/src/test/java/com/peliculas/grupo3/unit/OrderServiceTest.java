package com.peliculas.grupo3.unit;

import com.peliculas.grupo3.dto.CategoryDTO;
import com.peliculas.grupo3.dto.MovieDTO;
import com.peliculas.grupo3.dto.OrderDTO;
import com.peliculas.grupo3.dto.response.RoleResponseDTO;
import com.peliculas.grupo3.dto.response.UserResponseDTO;
import com.peliculas.grupo3.mapper.OrderMapper;
import com.peliculas.grupo3.mapper.OrderMapperImpl;
import com.peliculas.grupo3.model.*;
import com.peliculas.grupo3.repository.*;
import com.peliculas.grupo3.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private MovieRepository movieRepository;

    private RoleRepository roleRepository;

    private OrderMapper orderMapper;

    private CategoryRepository categoryRepository;

    Role userRole = Role.builder()
            .roleId(UUID.randomUUID())
            .description("Role for demo")
            .name("USER")
            .build();

    MovieUser user =MovieUser.builder()
            .userId(UUID.randomUUID())
            .phone("+57 305 123 432")
            .firstName("nombre")
            .lastName("apellido")
            .email("123@gmail.co")
            .phone("+57 305 123 432")
            .password("123")
            .address("address")
            .birthDate("10/10/2000")
            .role(Role.builder().name("USER").build())
            .build();

    Category comedia = Category.builder()
            .categoryId(UUID.randomUUID())
            .name("Comedia")
            .description("Peliculas de comedia")
            .build();

    Movie fastX = Movie.builder()
            .movieId(UUID.randomUUID())
            .name("Fast X")
            .description("Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path.")
            .category(comedia)
            .pgRating("PG-13")
            .imageURL("https://image.tmdb.org/t/p/original/1E5baAaEse26fej7uHcjOgEE2t2.jpg")
            .price(10L)
            .build();

    Movie spiderVerse2 = Movie.builder()
            .movieId(UUID.randomUUID())
            .name("Spider-Man: Across the Spider-Verse")
            .description("After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse.")
            .category(comedia)
            .imageURL("https://image.tmdb.org/t/p/original/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg")
            .pgRating("PG-13")
            .price(10L)
            .build();

    MovieOrder testOrder = MovieOrder.builder()
            .user(user)
            .orderId(UUID.randomUUID())
            .orderNumber("10")
            .total(10L)
            .movies(List.of(spiderVerse2))
            .status("confirmada")
            .build();

    @BeforeEach
    public void init(){
        categoryRepository=mock(CategoryRepository.class);
        orderRepository=mock(OrderRepository.class);
        userRepository=mock(UserRepository.class);
        movieRepository=mock(MovieRepository.class);
        roleRepository=mock(RoleRepository.class);
        orderMapper=spy(OrderMapperImpl.class);
        orderService = new OrderService(orderRepository,orderMapper,userRepository,movieRepository);
    }

    @Test
    public void testCreateOrder(){
        when(categoryRepository.findByName(any())).thenReturn(Optional.of(comedia));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
        when(roleRepository.findByName(any())).thenReturn(Optional.of(userRole));
        when(movieRepository.findByName(any())).thenReturn(Optional.of(fastX));
        orderService.save(defaultOrderDTO());
        MovieOrder order = testOrder;
        verify(orderRepository,times(1)).findByOrderNumber(argThat(name->name.equals(order.getOrderNumber())));
    }

    private OrderDTO defaultOrderDTO() {
        return OrderDTO.builder()
                .orderNumber("10")
                .movies(List.of(MovieDTO.builder()
                        .pgRating("PG-13")
                        .imageURL("https://image.tmdb.org/t/p/original/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg")
                        .price(10L)
                        .description("After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse.")
                        .name("Spider-Man: Across the Spider-Verse")
                        .categoryDTO(new CategoryDTO("Accion","Peliculas de accion"))
                        .build()))
                .status("confirmada")
                .total(10L)
                .user(UserResponseDTO.builder()
                        .email("noname@email.com")
                        .firstName("he who")
                        .lastName("must not be named")
                        .phone("+57123123123")
                        .role(RoleResponseDTO.builder()
                                .name("ADMIN")
                                .build())
                        .build()
                )
                .build();
    }
}
