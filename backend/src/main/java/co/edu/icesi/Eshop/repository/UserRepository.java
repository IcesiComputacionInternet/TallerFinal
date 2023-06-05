package co.edu.icesi.Eshop.repository;

import co.edu.icesi.Eshop.model.EShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<EShopUser, UUID> {

    @Query("SELECT user FROM EShopUser user WHERE  user.email= :email")
    Optional<EShopUser> findByEmail(String email);

    @Query("SELECT user FROM EShopUser user WHERE  user.phoneNumber= :phoneNumber")
    Optional<EShopUser> findByPhoneNumber(String phoneNumber);

}
