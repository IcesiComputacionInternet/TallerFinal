package co.com.icesi.eShopBackEnd.repository;

import co.com.icesi.eShopBackEnd.Enum.OrderState;
import co.com.icesi.eShopBackEnd.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, UUID> {

    @Query(value = "SELECT s FROM SalesOrder s WHERE s.orderId = :orderId")
    Optional<SalesOrder> returnOrder(UUID orderId);

    @Modifying
    @Query(value = "UPDATE SalesOrder s SET s.status = :state WHERE s.orderId = :orderId")
    void updateStateOrder(UUID orderId, OrderState state);
}
