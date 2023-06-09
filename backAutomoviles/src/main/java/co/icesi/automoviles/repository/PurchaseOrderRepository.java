package co.icesi.automoviles.repository;

import co.icesi.automoviles.model.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {
    @Query("SELECT purOrd FROM PurchaseOrder purOrd")
    Page<PurchaseOrder> getAllPurchaseOrders(Pageable pageable);

    @Query("SELECT purOrd FROM PurchaseOrder purOrd WHERE purOrd.eShopUser.eShopUserId = :id")
    Page<PurchaseOrder> getAllPurchaseOrdersById(@Param("id") UUID id, Pageable pageable);
}
