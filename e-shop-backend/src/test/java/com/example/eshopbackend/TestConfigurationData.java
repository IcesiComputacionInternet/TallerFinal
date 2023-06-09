package com.example.eshopbackend;

import com.example.eshopbackend.enums.OrderStatus;
import com.example.eshopbackend.model.*;
import com.example.eshopbackend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@TestConfiguration
public class TestConfigurationData {
    @Bean
    CommandLineRunner commandLineRunner(ItemRepository items,
                                        OrderRepository orders,
                                        CategoryRepository categorys,
                                        RoleRepository roles,
                                        UserRepository users) {

        Role role1 = Role.builder()
                .roleId(UUID.randomUUID())
                .description("Role for demo")
                .roleName("ADMIN")
                .build();
        Role role2 = Role.builder()
                .roleId(UUID.randomUUID())
                .description("Role for demo")
                .roleName("USER")
                .build();
        Role role3 = Role.builder()
                .roleId(UUID.randomUUID())
                .description("Role for demo")
                .roleName("SHOP")
                .build();
        User user1 = User.builder()
                .userId(UUID.randomUUID())
                .email("johndoe@gmail.com")
                .role(role1)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+57123123123")
                .address("Calle 123 # 123-123")
                .birthDate(LocalDateTime.now())
                .password(new BCryptPasswordEncoder().encode("password"))
                .build();
        User user2 = User.builder()
                .userId(UUID.randomUUID())
                .email("juanpalta@hotmail.com")
                .role(role2)
                .firstName("Juan")
                .lastName("Palta")
                .phoneNumber("+573152199142")
                .address("Calle 123 # 123-123")
                .birthDate(LocalDateTime.now())
                .password(new BCryptPasswordEncoder().encode("password"))
                .build();
        User user3 = User.builder()
                .userId(UUID.randomUUID())
                .email("daniloerazo@hotmail.com")
                .role(role3)
                .firstName("Danilo")
                .lastName("Erazo")
                .phoneNumber("+573112129343")
                .address("Calle 123 # 123-123")
                .birthDate(LocalDateTime.now())
                .password(new BCryptPasswordEncoder().encode("password"))
                .build();
        User user4 = User.builder()
                .userId(UUID.randomUUID())
                .email("juanfelipe@hotmail.com")
                .role(role2)
                .firstName("Juan")
                .lastName("Felipe")
                .address("Calle 123 # 123-123")
                .birthDate(LocalDateTime.now())
                .phoneNumber("+5712344123")
                .password(new BCryptPasswordEncoder().encode("password"))
                .build();
        Category category1 = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Abeto")
                .description("Fir wood")
                .build();
        Category category2 = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Cedro")
                .description("Cedar wood")
                .build();
        Category category3 = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Fresno")
                .description("Ash wood")
                .build();
        Category category4 = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Nogal")
                .description("Walnut wood")
                .build();
        Category category5 = Category.builder()
                .categoryId(UUID.randomUUID())
                .name("Cerezo")
                .description("Cherry wood")
                .build();
        Item item1 = Item.builder()
                .itemId(UUID.randomUUID())
                .name("Sofa Abeto")
                .description("Acabados en Abeto")
                .price((long) 1000.0)
                .warranty(1)
                .imageUrl("https://images.pexels.com/photos/1866149/pexels-photo-1866149.jpeg")
                .category(category1).build();
        Item item2 = Item.builder()
                .itemId(UUID.randomUUID())
                .name("Silla Cedro")
                .description("Acabados en Cedro")
                .price((long) 1000.0)
                .warranty(1)
                .imageUrl("https://images.pexels.com/photos/5028853/pexels-photo-5028853.jpeg")
                .category(category2).build();
        Item item3 = Item.builder()
                .itemId(UUID.randomUUID())
                .name("Cama Fresno")
                .description("Acabados en Fresno")
                .price((long) 2000.0)
                .warranty(1)
                .imageUrl("https://images.pexels.com/photos/279746/pexels-photo-279746.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                .category(category3).build();
        Item item4 = Item.builder()
                .itemId(UUID.randomUUID())
                .name("Mesa Nogal")
                .description("Acabados en Nogal")
                .price((long) 3000.0)
                .warranty(1)
                .imageUrl("https://images.pexels.com/photos/631411/pexels-photo-631411.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                .category(category4).build();
        Item item5 = Item.builder()
                .itemId(UUID.randomUUID())
                .name("Comedor Cerezo")
                .description("Acabados en Cerezo")
                .price((long) 4000.0)
                .warranty(1)
                .imageUrl("https://images.pexels.com/photos/460537/pexels-photo-460537.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                .category(category5).build();
        Order order1 = Order.builder()
                .orderId(UUID.randomUUID())
                .user(user2)
                .status(OrderStatus.PENDING)
                .items(new ArrayList<>(List.of(item1, item2, item3)))
                .build();
        Order order2 = Order.builder()
                .orderId(UUID.randomUUID())
                .user(user4)
                .status(OrderStatus.PROCESS)
                .items(new ArrayList<>(List.of(item4, item5)))
                .build();

        return args -> {
            roles.save(role1);
            roles.save(role2);
            roles.save(role3);
            users.save(user1);
            users.save(user2);
            users.save(user3);
            users.save(user4);
            categorys.save(category1);
            categorys.save(category2);
            categorys.save(category3);
            categorys.save(category4);
            categorys.save(category5);
            items.save(item1);
            items.save(item2);
            items.save(item3);
            items.save(item4);
            items.save(item5);
            orders.save(order1);
            orders.save(order2);
        };
    }
}
