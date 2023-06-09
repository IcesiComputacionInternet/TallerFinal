package co.icesi.automoviles.repository;
import java.util.Optional;
import java.util.UUID;

import co.icesi.automoviles.model.EShopUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EShopUserRepository extends JpaRepository<EShopUser, UUID> {
    Optional<EShopUser> findByEmail(String email);
    Optional<EShopUser> findByPhoneNumber(String phoneNumber);
    @Query( "SELECT u " +
            "FROM EShopUser u ")
    Page<EShopUser> getAllUsers(Pageable pageable);
}
