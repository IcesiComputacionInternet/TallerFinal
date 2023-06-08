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
		String BASE_URL = "/api/v1";

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

		UserPermission test = UserPermission.builder()
				.permissionId(UUID.randomUUID())
				.key("ADD_ITEMS")
				.path(BASE_URL + "/*0")
				.roles(List.of(ADMIN))
				.build();

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

		permissionRepository.save(test);
		userRepository.save(admin);
		userRepository.save(shop);

		return (args) -> System.out.println("loadData");
	}

}
