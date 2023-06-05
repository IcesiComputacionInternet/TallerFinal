package co.edu.icesi.Eshop.repository;

import co.edu.icesi.Eshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query("SELECT role FROM Role role WHERE role.roleName = :name")
    Optional<Role> findByName(String name);
}
