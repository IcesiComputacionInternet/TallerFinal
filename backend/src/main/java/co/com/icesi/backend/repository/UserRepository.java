package co.com.icesi.backend.repository;

import co.com.icesi.backend.model.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<ShopUser, UUID> {
    @Query("SELECT user FROM ShopUser user WHERE  user.email= :email")
    Optional<ShopUser> findByEmail(@Param("email") String email);
    @Query("SELECT user FROM ShopUser user WHERE user.phoneNumber = :phoneNumber")
    Optional<ShopUser> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
