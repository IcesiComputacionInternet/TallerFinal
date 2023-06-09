package co.com.icesi.eShopBackEnd.repository;

import co.com.icesi.eShopBackEnd.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query(value = "SELECT u FROM Customer u WHERE u.email = :email")
    Optional<Customer> findUserByEmail(String email);

    @Query(value = "SELECT CASE WHEN(COUNT(*) > 0) THEN true ELSE false END FROM Customer u WHERE u.email = :email")
    boolean findByEmail(String email);

    @Query(value = "SELECT CASE WHEN(COUNT(*) > 0) THEN true ELSE false END FROM Customer u WHERE u.phoneNumber = :phoneNumber")
    boolean findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT c FROM Customer c WHERE c.customerId = :idNumber")
    Optional<Customer> getCustomerById(UUID idNumber);

    @Modifying
    @Query("UPDATE Customer customer SET customer = :customer WHERE customer.email = :email")
    void uptadeInformation(Customer customer, String email);


}
