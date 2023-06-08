package co.com.icesi.backend.repository;

import co.com.icesi.backend.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<ShopOrder, UUID> {
    @Query("SELECT order FROM ShopOrder order WHERE order.status = :status AND order.shopUser.email = :userEmail")
    Optional<ShopOrder> findByStatus(@Param("status") String status, @Param("userEmail")String userEmail);
}
