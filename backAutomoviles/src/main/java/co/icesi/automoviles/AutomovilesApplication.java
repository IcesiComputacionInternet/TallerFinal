package co.icesi.automoviles;

import co.icesi.automoviles.enums.RoleType;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.model.EShopUser;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.model.Role;
import co.icesi.automoviles.repository.CategoryRepository;
import co.icesi.automoviles.repository.EShopUserRepository;
import co.icesi.automoviles.repository.ItemRepository;
import co.icesi.automoviles.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class AutomovilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomovilesApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(EShopUserRepository eShopUserRepository,
										RoleRepository roleRepository,
										CategoryRepository categoryRepository,
										ItemRepository itemRepository,
										PasswordEncoder encoder) {
		Role eShopUserRole1 = Role.builder()
				.roleId(UUID.fromString("f218a75c-c6af-4f1e-a2c6-b2b47f1a0678"))
				.description("Role for demo")
				.roleName(RoleType.ADMIN.toString())
				.build();
		Role eShopUserRole2 = Role.builder()
				.roleId(UUID.fromString("4fd2fef7-e595-48fe-a368-94edd6e142ee"))
				.description("Role for demo")
				.roleName(RoleType.USER.toString())
				.build();
		Role eShopUserRole3 = Role.builder()
				.roleId(UUID.fromString("3fd2fef7-e595-48fe-a368-94edd6e142ee"))
				.description("Role for demo")
				.roleName(RoleType.SHOP.toString())
				.build();
		EShopUser eShopUser1 = EShopUser.builder()
				.eShopUserId(UUID.fromString("04dacbf5-4815-4d6e-a2a7-db01a607f237"))
				.role(eShopUserRole1)
				.firstName("John")
				.lastName("Doe")
				.email("johndoe1@email.com")
				.phoneNumber("+573101234567")
				.password(encoder.encode("password"))
				.build();
		EShopUser eShopUser2 = EShopUser.builder()
				.eShopUserId(UUID.fromString("df17e266-dcc4-4bf2-923c-bb5559722f50"))
				.firstName("John")
				.lastName("Doe")
				.email("johndoe2@email.com")
				.role(eShopUserRole2)
				.phoneNumber("+573111234567")
				.password(encoder.encode("password"))
				.build();
		EShopUser eShopUser3 = EShopUser.builder()
				.eShopUserId(UUID.fromString("af17e266-dcc4-4bf2-923c-bb5559722f50"))
				.firstName("John")
				.lastName("Doe")
				.email("johndoe3@email.com")
				.role(eShopUserRole3)
				.phoneNumber("+573121234567")
				.password(encoder.encode("password"))
				.build();
		Category sportCategory = Category.builder()
				.categoryId(UUID.fromString("246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae"))
				.name("Sport")
				.description("Sport category")
				.items(null)
				.build();
		Item car1 = Item.builder()
				.itemId(UUID.fromString("e9c14553-3e76-4968-b78c-0d6fc8dfcdbb"))
				.description("Description for car 1")
				.name("Car 1")
				.price(10000000)
				.imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
				.category(sportCategory)
				.build();
		Item car2 = Item.builder()
				.itemId(UUID.fromString("30eadfff-5cc6-4968-9755-11de28678e38"))
				.description("Description for car 2")
				.name("Car 2")
				.price(10000000)
				.imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
				.category(sportCategory)
				.build();
		Item car3 = Item.builder()
				.itemId(UUID.fromString("5f81aa60-fb85-4de6-b7c4-824ea6b7fdf1"))
				.description("Description for car 3")
				.name("Car 3")
				.price(10000000)
				.imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
				.category(sportCategory)
				.build();
		Item car4 = Item.builder()
				.itemId(UUID.fromString("71f244d1-5128-4460-921f-4fa8a0d096c0"))
				.description("Description for car 4")
				.name("Car 4")
				.price(10000000)
				.imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
				.category(sportCategory)
				.build();
		Item car5 = Item.builder()
				.itemId(UUID.fromString("bcc455da-3b03-4ab8-adbb-d45754a6365a"))
				.description("Description for car 5")
				.name("Car 5")
				.price(10000000)
				.imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
				.category(sportCategory)
				.build();
		Item car6 = Item.builder()
				.itemId(UUID.fromString("bcc455da-3b03-4ab8-adbb-d45754a7365a"))
				.description("Description for car 6")
				.name("Car 6")
				.price(10000000)
				.imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
				.category(sportCategory)
				.build();
		Item car7 = Item.builder()
				.itemId(UUID.fromString("bcc455da-3b03-4a18-adbb-d45754a6365a"))
				.description("Description for car 7")
				.name("Car 7")
				.price(10000000)
				.imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
				.category(sportCategory)
				.build();
		Item car8 = Item.builder()
				.itemId(UUID.fromString("bcc455da-3b03-4ab8-adbb-d25754a6365a"))
				.description("Description for car 8")
				.name("Car 8")
				.price(10000000)
				.imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
				.category(sportCategory)
				.build();

		return args -> {
			roleRepository.save(eShopUserRole1);
			roleRepository.save(eShopUserRole2);
			roleRepository.save(eShopUserRole3);
			eShopUserRepository.save(eShopUser1);
			eShopUserRepository.save(eShopUser2);
			eShopUserRepository.save(eShopUser3);
			categoryRepository.save(sportCategory);
			itemRepository.save(car1);
			itemRepository.save(car2);
			itemRepository.save(car3);
			itemRepository.save(car4);
			itemRepository.save(car5);
			itemRepository.save(car6);
			itemRepository.save(car7);
			itemRepository.save(car8);
		};
	}
}
