package co.com.icesi.Eshop.repository;

import co.com.icesi.Eshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
