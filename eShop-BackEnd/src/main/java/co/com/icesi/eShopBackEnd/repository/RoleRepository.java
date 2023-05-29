package co.com.icesi.eShopBackEnd.repository;

import co.com.icesi.eShopBackEnd.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query(value = "SELECT CASE WHEN(COUNT(*) > 0) THEN true ELSE false END FROM Role r WHERE r.roleName = :name")
    boolean roleExists(String name);

    @Query(value = "SELECT r FROM Role r WHERE r.roleName = :name")
    Optional<Role> findByRoleName(String name);

}
