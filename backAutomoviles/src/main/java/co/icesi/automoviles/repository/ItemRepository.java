package co.icesi.automoviles.repository;

import co.icesi.automoviles.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Query("SELECT itm FROM Item itm")
    Page<Item> getAllItems(Pageable pageable);
}
