package co.com.icesi.eShop_Back;

import co.com.icesi.eShop_Back.model.Role;
import co.com.icesi.eShop_Back.model.User;
import co.com.icesi.eShop_Back.model.security.UserPermission;
import co.com.icesi.eShop_Back.repository.PermissionRepository;
import co.com.icesi.eShop_Back.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class EShopBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShopBackApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(
			PermissionRepository permissionRepository,
			UserRepository userRepository,
			PasswordEncoder encoder
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
				.roles(List.of(ADMIN))
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
		permissionRepository.save(users);
		permissionRepository.save(roles);
		permissionRepository.save(categories);
		permissionRepository.save(orders);
		permissionRepository.save(items);
		permissionRepository.save(getItems);
		userRepository.save(admin);
		userRepository.save(shop);

		return (args) -> System.out.println("loadData");
	}

}
