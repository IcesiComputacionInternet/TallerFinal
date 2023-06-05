package co.com.icesi.backend.repository;

import co.com.icesi.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("SELECT role FROM Role role WHERE role.roleName = :roleName")
    Optional<Role> findByName(@Param("roleName") String roleName);

    @Query("SELECT COUNT(role) > 0 FROM Role role WHERE role.roleName = :roleName")
    Boolean isNameInUse(@Param("roleName") String roleName);
}
