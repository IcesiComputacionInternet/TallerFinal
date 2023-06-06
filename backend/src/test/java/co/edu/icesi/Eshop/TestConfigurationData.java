package co.edu.icesi.Eshop;

import co.edu.icesi.Eshop.mapper.ItemMapperImpl;
import co.edu.icesi.Eshop.model.*;
import co.edu.icesi.Eshop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestConfiguration
public class TestConfigurationData {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        CategoryRepository categoryRepository,
                                        RoleRepository roleRepository,
                                        OrderRepository orderRepository,
                                        ItemRepository itemRepository,
                                        PasswordEncoder passwordEncoder){

        Role role1 = Role.builder()
                .roleId(UUID.randomUUID())
                .roleName(Roles.ADMIN.toString())
                .description("An admin role")
                .build();
        Role role2 = Role.builder()
                .roleId(UUID.randomUUID())
                .roleName(Roles.SHOP.toString())
                .description("A shop role")
                .build();
        Role role3 = Role.builder()
                .roleId(UUID.randomUUID())
                .roleName(Roles.USER.toString())
                .description("An user role")
                .build();

        Category category1 = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Cuidado del hogar")
                .description("Articulos para el cuidado del hogar")
                .build();
        Category category2 = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Refrigeracion")
                .description("Articulos para refrigeracion")
                .build();

        Item item1 = Item.builder()
                .itemId(UUID.randomUUID())
                .name("Plancha 30V")
                .description("Plancha para cualquier ropa")
                .category(category1)
                .imageUrl("https://www.alkosto.com/medias/053891144155-001-750Wx750H?context=bWFzdGVyfGltYWdlc3wxMDg2NTR8aW1hZ2UvanBlZ3xpbWFnZXMvaDRjL2hmZS8xMTQwMjA1NTMxOTU4Mi5qcGd8MTAyYWI3ZjExZDA3OThiZmNjZWVhZGMzZmI4Y2MwMTFjMGEwNTBkY2E1NzljMTZiZjRhNjJhYmNjZTMzNGFkOQ")
                .price(200000L)
                .minVoltage(1.5)
                .maxVoltage(3.0)
                .sourceOfEnergy("Energía por cable")
                .levelOfEfficiency("B")
                .marca("Oster")
                .model("2023")
                .guarantee(12)
                .available(true)
                .build();
        Item item2 = Item.builder()
                .itemId(UUID.randomUUID())
                .name("Nevera 250L")
                .description("Nevera grande")
                .category(category2)
                .imageUrl("https://falabella.scene7.com/is/image/FalabellaCO/19089838_1?wid=1500&hei=1500&qlt=70")
                .price(1700000L)
                .minVoltage(2.0)
                .maxVoltage(4.8)
                .sourceOfEnergy("Energía por cable")
                .levelOfEfficiency("A")
                .marca("Kalley")
                .model("2024")
                .guarantee(36)
                .available(true)
                .build();

        EShopUser user1 = EShopUser.builder()
                .userId(UUID.randomUUID())
                .email("johndoe@email.com")
                .password(passwordEncoder.encode("password"))
                .role(role1)
                .build();
        EShopUser user2 = EShopUser.builder()
                .userId(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+573181655564")
                .email("johndoe2@email.com")
                .birthday(LocalDate.of(2000, 12, 7))
                .role(role2)
                .password(passwordEncoder.encode("password"))
                .build();
        EShopUser user3 = EShopUser.builder()
                .userId(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+573181485564")
                .email("johndoe3@email.com")
                .birthday(LocalDate.of(2002, 3, 27))
                .role(role3)
                .password(passwordEncoder.encode("password"))
                .address("955 Steele Street")
                .build();

        EShopOrder order1 = EShopOrder.builder()
                .orderId(UUID.randomUUID())
                .status(Status.PENDING.toString())
                .total(item1.getPrice())
                .eShopUser(user3)
                .items(Stream.of(item1).collect(Collectors.toList()))
                .build();
        EShopOrder order2 = EShopOrder.builder()
                .orderId(UUID.fromString("d1e854e9-129e-4958-80b5-23599d72d42b"))
                .status(Status.RECEIVED.toString())
                .total(item2.getPrice())
                .eShopUser(user3)
                .items(Stream.of(item1,item2).collect(Collectors.toList()))
                .build();

        return args -> {
            userRepository.save(user1);
            userRepository.save(user2);
            itemRepository.save(item1);
            itemRepository.save(item2);
            orderRepository.save(order1);
            orderRepository.save(order2);

        };
    }
}
