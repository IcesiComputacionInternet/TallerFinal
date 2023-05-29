package co.com.icesi.eShopBackEnd.repository;

import co.com.icesi.eShopBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

}
