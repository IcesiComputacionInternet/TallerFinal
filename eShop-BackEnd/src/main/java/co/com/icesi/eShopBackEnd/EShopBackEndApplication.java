package co.com.icesi.eShopBackEnd;

import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.model.User;
import co.com.icesi.eShopBackEnd.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
public class EShopBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShopBackEndApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(UserRepository users,
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

		User adminUser = User.builder()
				.userId(UUID.randomUUID())
				.firstName("John")
				.lastName("Doe")
				.email("jhonDoe@email.com")
				.password(encoder.encode("password"))
				.phoneNumber("+573258690127")
				.address("Calle 123")
				.birthday(date1)
				.role(admin)
				.build();

		User normalUser = User.builder()
				.userId(UUID.randomUUID())
				.firstName("Zara")
				.lastName("Gomez")
				.email("z@email.com")
				.password(encoder.encode("password"))
				.phoneNumber("+573258691487")
				.address("Avenida 6ta")
				.birthday(date1)
				.role(user)
				.build();

		users.save(adminUser);
		users.save(normalUser);
		return args -> {

		};

	}

}
