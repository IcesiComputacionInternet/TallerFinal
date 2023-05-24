package co.com.icesi.eShop_Back;

import co.com.icesi.eShop_Back.model.Role;
import co.com.icesi.eShop_Back.model.security.UserPermission;
import co.com.icesi.eShop_Back.repository.PermissionRepository;
import co.com.icesi.eShop_Back.repository.RoleRepository;
import co.com.icesi.eShop_Back.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			UserRepository userRepository
	) {
		String BASE_URL = "/api/v1/";

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

//		UserPermission addItems = UserPermission.builder()
//				.permissionId(UUID.randomUUID())
//				.key("ADD_ITEMS")
//				.path(BASE_URL + "items/")
//				.roles(List.of(ADMIN, SHOP))
//				.build();

		return (args) -> {
			System.out.println("Hello world");
		};
	}

}
