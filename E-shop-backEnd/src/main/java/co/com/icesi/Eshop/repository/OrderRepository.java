package co.com.icesi.Eshop.repository;

import co.com.icesi.Eshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
