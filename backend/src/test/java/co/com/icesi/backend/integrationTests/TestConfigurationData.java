package co.com.icesi.backend.integrationTests;

import co.com.icesi.backend.model.Role;
import co.com.icesi.backend.repository.RoleRepository;
import co.com.icesi.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@TestConfiguration
public class TestConfigurationData {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        Role role = Role.builder()
                .roleId(UUID.randomUUID())
                .description("Role for demo")
                .roleName("ADMIN")
                .build();
        Role role2 = Role.builder()
                .roleId(UUID.randomUUID())
                .description("Role for demo")
                .roleName("SHOP")
                .build();
        Role role3 = Role.builder()
                .roleId(UUID.randomUUID())
                .description("Role for demo")
                .roleName("USER")
                .build();

        return args -> {
            roleRepository.save(role);
            roleRepository.save(role2);
            roleRepository.save(role3);
        };
    }
}
