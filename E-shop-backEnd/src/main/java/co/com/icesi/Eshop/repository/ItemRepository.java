package co.com.icesi.Eshop.repository;

import co.com.icesi.Eshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
