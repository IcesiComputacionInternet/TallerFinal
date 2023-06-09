package co.icesi.automoviles.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.icesi.automoviles.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.icesi.automoviles.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(String roleName);
    @Query( "SELECT r " +
            "FROM Role r")
    Page<Role> getAllRoles(Pageable pageable);
}
