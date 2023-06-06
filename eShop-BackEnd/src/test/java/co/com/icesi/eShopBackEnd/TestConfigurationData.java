package co.com.icesi.eShopBackEnd;


import co.com.icesi.eShopBackEnd.model.Customer;
import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
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
                                        PasswordEncoder encoder) {

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







        return args -> {
            users.save(adminCustomer);
            users.save(normalCustomer);
        };

    }





}
