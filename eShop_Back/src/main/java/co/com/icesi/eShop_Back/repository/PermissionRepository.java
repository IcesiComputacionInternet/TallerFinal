package co.com.icesi.eShop_Back.repository;

import co.com.icesi.eShop_Back.model.security.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository  extends JpaRepository<UserPermission, UUID> {
}
