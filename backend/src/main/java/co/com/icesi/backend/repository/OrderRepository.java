package co.com.icesi.backend.repository;

import co.com.icesi.backend.model.Cellphone;
import co.com.icesi.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT order FROM Order order WHERE order.status = :status AND order.user.email = :userEmail")
    Optional<Order> findByStatus(@Param("status") String status, @Param("userEmail")String userEmail);
}
