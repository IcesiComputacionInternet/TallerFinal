package co.com.icesi.eShop_Back;

import co.com.icesi.eShop_Back.enums.Status;
import co.com.icesi.eShop_Back.model.*;
import co.com.icesi.eShop_Back.model.security.UserPermission;
import co.com.icesi.eShop_Back.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class EShopBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShopBackApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner loadData(
			PermissionRepository permissionRepository,
			UserRepository userRepository,
			CategoryRepository categoryRepository,
			PasswordEncoder encoder,
			ItemRepository itemRepository,
			OrderRepository orderRepository
	) {
		String BASE_URL = "/api";

		////////////////////////////////////////
		Role ADMIN = Role.builder()
				.roleId(UUID.randomUUID())
				.name("ADMIN")
				.description("Administrator")
				.build();

		Role SHOP = Role.builder()
				.roleId(UUID.randomUUID())
				.name("SHOP")
				.description("Shop")
				.build();

		Role CLIENT = Role.builder()
				.roleId(UUID.randomUUID())
				.name("CLIENT")
				.description("Client")
				.build();
		////////////////////////////////////////
		UserPermission users = UserPermission.builder()
				.permissionId(UUID.randomUUID())
				.key("USERS")
				.path(BASE_URL + "/users/**")
				.roles(List.of(ADMIN))
				.build();
		UserPermission roles = UserPermission.builder()
				.permissionId(UUID.randomUUID())
				.key("USERS")
				.path(BASE_URL + "/roles/**")
				.roles(List.of(ADMIN))
				.build();
		UserPermission categories = UserPermission.builder()
				.permissionId(UUID.randomUUID())
				.key("USERS")
				.path(BASE_URL + "/categories/**")
				.roles(List.of(ADMIN, SHOP, CLIENT))
				.build();
		UserPermission orders = UserPermission.builder()
				.permissionId(UUID.randomUUID())
				.key("ORDERS")
				.path(BASE_URL + "/orders/**")
				.roles(List.of(ADMIN, SHOP, CLIENT))
				.build();
		UserPermission items = UserPermission.builder()
				.permissionId(UUID.randomUUID())
				.key("ADD_ITEMS")
				.path(BASE_URL + "/items/**")
				.roles(List.of(ADMIN, SHOP))
				.build();
		UserPermission getItems = UserPermission.builder()
				.permissionId(UUID.randomUUID())
				.key("GET_ITEMS")
				.path(BASE_URL + "/items/get/**")
				.roles(List.of(ADMIN, CLIENT))
				.build();
		////////////////////////////////////////
		User admin = User.builder()
				.userId(UUID.randomUUID())
				.email("admin@gmail.com")
				.password(encoder.encode("123"))
				.role(ADMIN)
				.build();
		User shop = User.builder()
				.userId(UUID.randomUUID())
				.email("shop@gmail.com")
				.password(encoder.encode("123"))
				.role(SHOP)
				.build();
		////////////////////////////////////////
		Category desktop = Category.builder()
				.categoryId(UUID.randomUUID())
				.name("Desktop")
				.description("Desktop")
				.imageUrl("https://raw.githubusercontent.com/IcesiComputacionInternet/TallerFinal/Grupo_5/eShop_Front/src/resources/images/desktop.webp")
				.build();
		Category gaming = Category.builder()
				.categoryId(UUID.randomUUID())
				.name("All in one")
				.description("Gaming")
				.imageUrl("https://raw.githubusercontent.com/IcesiComputacionInternet/TallerFinal/Grupo_5/eShop_Front/src/resources/images/all-in-one.png")
				.build();
		Category pcGamer = Category.builder()
				.categoryId(UUID.randomUUID())
				.name("PC Gamer")
				.description("PC Gamer")
				.imageUrl("https://raw.githubusercontent.com/IcesiComputacionInternet/TallerFinal/Grupo_5/eShop_Front/src/resources/images/gaming.webp")
				.build();
		Category laptop = Category.builder()
				.categoryId(UUID.randomUUID())
				.name("Laptop")
				.description("Laptop")
				.imageUrl("https://raw.githubusercontent.com/IcesiComputacionInternet/TallerFinal/Grupo_5/eShop_Front/src/resources/images/portatiles.webp")
				.build();

		Item item1 = Item.builder()
				.itemId(UUID.randomUUID())
				.name("item1")
				.description("este es el item 1")
				.price(1000L)
				.imageUrl("")
				.category(desktop)
				.build();

		List<Item> itemsS = new ArrayList<>();
		itemsS.add(item1);

		Order order1 = Order.builder()
				.orderId(UUID.randomUUID())
				.user(admin)
				.status(Status.PENDING)
				.total(1000L)
				.items(itemsS)
				.build();

		Order order2 = Order.builder()
				.orderId(UUID.randomUUID())
				.user(admin)
				.status(Status.DELIVERED)
				.total(1000L)
				.items(itemsS)
				.build();


		////////////////////////////////////////
		permissionRepository.save(users);
		permissionRepository.save(roles);
		permissionRepository.save(categories);
		permissionRepository.save(orders);
		permissionRepository.save(items);
		permissionRepository.save(getItems);
		categoryRepository.save(desktop);
		categoryRepository.save(gaming);
		categoryRepository.save(pcGamer);
		categoryRepository.save(laptop);
		userRepository.save(admin);
		userRepository.save(shop);
		itemRepository.save(item1);
		orderRepository.save(order1);
		orderRepository.save(order2);


		return (args) -> System.out.println("loadData");
	}

}
