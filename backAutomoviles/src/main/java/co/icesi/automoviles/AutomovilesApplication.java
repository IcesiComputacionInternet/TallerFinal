package co.icesi.automoviles;

import co.icesi.automoviles.enums.RoleType;
import co.icesi.automoviles.model.EShopUser;
import co.icesi.automoviles.model.Role;
import co.icesi.automoviles.repository.EShopUserRepository;
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

		return args -> {
			roleRepository.save(eShopUserRole1);
			roleRepository.save(eShopUserRole2);
			roleRepository.save(eShopUserRole3);
			eShopUserRepository.save(eShopUser1);
			eShopUserRepository.save(eShopUser2);
			eShopUserRepository.save(eShopUser3);
		};
	}
}
