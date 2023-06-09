package co.com.icesi.eShopBackEnd;

import co.com.icesi.eShopBackEnd.model.Category;
import co.com.icesi.eShopBackEnd.model.Customer;
import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.repository.CategoryRepository;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
public class EShopBackEndApplication {
	/*
	Una orden pertenece a un user
	Un user puede tener muchas ordenes

	Un rol puede pertenecer a muchos usuarios
	Muchos usuarios tienen UN rol

	Una orden tiene muchos items
	Los items pueden pertenecer a muchas ordenes

	Muchos items tienen UNA category
	Una category puede pertenecer a muchos items
	 */

	public static void main(String[] args) {
		SpringApplication.run(EShopBackEndApplication.class, args);
	}
	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(CustomerRepository users, CategoryRepository categories,
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
		Category category = Category.builder()
				.categoryId(UUID.randomUUID())
				.name("Category 1")
				.description("Category 1")
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
			categories.save(category);
		};

	}

}
