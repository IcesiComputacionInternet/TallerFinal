package co.com.icesi.eShopBackEnd;


import co.com.icesi.eShopBackEnd.dto.AssignCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import co.com.icesi.eShopBackEnd.model.Category;
import co.com.icesi.eShopBackEnd.model.Customer;
import co.com.icesi.eShopBackEnd.model.Item;
import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.repository.CategoryRepository;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
import co.com.icesi.eShopBackEnd.repository.ItemRepository;
import co.com.icesi.eShopBackEnd.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

@TestConfiguration
public class TestConfigurationData {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository users,
                                        PasswordEncoder encoder, CategoryRepository categoryRepository, ItemRepository itemRepository) {

        Role admin = Role.builder()
                .roleId(UUID.randomUUID())
                .roleName("ADMIN")
                .description("Role Admin")
                .build();






        Role user = Role.builder()
                .roleId(UUID.randomUUID())
                .roleName("USER")
                .description("Role User")
                .build();





        Role shop = Role.builder()
                .roleId(UUID.randomUUID())
                .roleName("SHOP")
                .description("Role Shop")
                .build();






        LocalDate date1 = LocalDate.of(1990, 1, 1);

        Customer adminCustomer = Customer.builder()
                .customerId(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .email("jhonDoe@email.com")
                .password(encoder.encode("password"))
                .phoneNumber("+573258690127")
                .address("Calle 123")
                .birthday(date1)
                .role(admin)
                .build();





        Customer normalCustomer = Customer.builder()
                .customerId(UUID.randomUUID())
                .firstName("Zara")
                .lastName("Gomez")
                .email("z@email.com")
                .password(encoder.encode("password"))
                .phoneNumber("+573258691487")
                .address("Avenida 6ta")
                .birthday(date1)
                .role(user)
                .build();



        Customer shopCustomer = Customer.builder()
                .customerId(UUID.randomUUID())
                .firstName("Zara")
                .lastName("Gomez")
                .email("zas@email.com")
                .password(encoder.encode("password"))
                .phoneNumber("+573268691487")
                .address("Avenida 6ta")
                .birthday(date1)
                .role(shop)
                .build();

        Category assignCategory = Category.builder()
                .name("Television")
                .categoryId(UUID.randomUUID())
                .description("Teacher")
                .build();
        Item item=  Item.builder()
                .name("itemValid")
                .itemId(UUID.randomUUID())
                .category(assignCategory)
                .description("Is a teacher the university Icesi")
                .build();

        return args -> {

            users.save(adminCustomer);
            users.save(normalCustomer);
            users.save(shopCustomer);
            categoryRepository.save(assignCategory);
            itemRepository.save(item);

        };

    }





}
