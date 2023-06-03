package co.com.icesi.Eshop.repository;

import co.com.icesi.Eshop.model.OrderStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderStore, UUID> {

}
