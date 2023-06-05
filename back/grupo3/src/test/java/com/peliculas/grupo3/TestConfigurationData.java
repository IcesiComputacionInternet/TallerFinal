package com.peliculas.grupo3;

import com.peliculas.grupo3.model.*;
import com.peliculas.grupo3.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@TestConfiguration
public class TestConfigurationData {

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository,
                                               MovieRepository movieRepository,
                                               OrderRepository orderRepository,
                                               PasswordEncoder encoder,
                                               RoleRepository roleRepository,
                                               CategoryRepository categoryRepository) {
        Category accion = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Accion")
                .description("Peliculas de accion")
                .build();
        Category comedia = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Comedia")
                .description("Peliculas de comedia")
                .build();
        Category drama = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Drama")
                .description("Peliculas de drama")
                .build();
        Category terror = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Terror")
                .description("Peliculas de terror")
                .build();
        Category romance = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Romance")
                .description("Peliculas de romance")
                .build();
        Category fantasia = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Fantasia")
                .description("Peliculas de fantasia")
                .build();
        Category cienciaFiccion = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Ciencia ficcion")
                .description("Peliculas de ciencia ficcion")
                .build();
        Category musical = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Musical")
                .description("Peliculas de musical")
                .build();
        Category animacion = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Animacion")
                .description("Peliculas de animacion")
                .build();
        Category documental = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Documental")
                .description("Peliculas de documental")
                .build();
        Category infantil = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Infantil")
                .description("Peliculas infantiles")
                .build();

        Role admin = Role.builder()
                .roleId(UUID.randomUUID())
                .description("Role for demo")
                .name("ADMIN")
                .build();
        Role user = Role.builder()
                .roleId(UUID.randomUUID())
                .description("Role for demo")
                .name("USER")
                .build();
        Movie spiderVerse2 = Movie.builder()
                .movieId(UUID.randomUUID())
                .name("Spider-Man: Across the Spider-Verse")
                .description("After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse.")
                .category(accion)
                .imageURL("https://image.tmdb.org/t/p/original/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg")
                .pgRating("PG-13")
                .price(10L)
                .build();
        Movie jhonWick4 = Movie.builder()
                .movieId(UUID.randomUUID())
                .name("John Wick: Chapter 4")
                .description("With the price on his head ever increasing, John Wick uncovers a path to defeating The High Table.")
                .category(accion)
                .pgRating("PG-13")
                .imageURL("https://image.tmdb.org/t/p/original/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg")
                .price(10L)
                .build();
        Movie superMarioBros = Movie.builder()
                .movieId(UUID.randomUUID())
                .name("The Super Mario Bros. Movie")
                .description("While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world.")
                .category(animacion)
                .pgRating("PG")
                .imageURL("https://image.tmdb.org/t/p/original/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg")
                .price(10L)
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

        MovieUser adminUser = MovieUser.builder()
                .userId(UUID.randomUUID())
                .firstName("he who")
                .lastName("must not be named")
                .email("noname@email.com")
                .phone("+57123123123")
                .address("Calle 123 # 123-123")
                .birthDate("1990-01-01")
                .password(encoder.encode("password"))
                .role(admin)
                .build();
        MovieUser normalUser = MovieUser.builder()
                .userId(UUID.randomUUID())
                .firstName("skill")
                .lastName("issue")
                .address("Calle 123 # 123-123")
                .email("chillguy@email.com")
                .phone("+57123124123")
                .birthDate("2000-01-01")
                .password(encoder.encode("password"))
                .role(user)
                .build();

        MovieOrder order1 = MovieOrder.builder()
                .orderId(UUID.randomUUID())
                .user(adminUser)
                .orderNumber("1")
                .movies(List.of(spiderVerse2, jhonWick4))
                .total(20L)
                .status("confirmada")
                .user(adminUser)
                .build();

        MovieOrder order2 = MovieOrder.builder()
                .orderId(UUID.randomUUID())
                .user(normalUser)
                .orderNumber("2")
                .movies(List.of(superMarioBros, fastX))
                .total(20L)
                .status("en proceso")
                .user(normalUser)
                .build();


        return args -> {
            categoryRepository.saveAll(List.of(accion, comedia, terror, romance, fantasia, cienciaFiccion, musical, animacion, documental, infantil, drama));
            roleRepository.saveAll(List.of(admin, user));
            movieRepository.saveAll(List.of(spiderVerse2, jhonWick4, superMarioBros, fastX));
            userRepository.saveAll(List.of(adminUser, normalUser));
            orderRepository.saveAll(List.of(order1, order2));
            adminUser.setMovieOrders(List.of(order1));
            normalUser.setMovieOrders(List.of(order2));
        };
    }

}
