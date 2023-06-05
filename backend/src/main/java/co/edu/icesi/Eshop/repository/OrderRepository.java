package co.edu.icesi.Eshop.repository;

import co.edu.icesi.Eshop.model.EShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<EShopOrder, UUID> {
}
