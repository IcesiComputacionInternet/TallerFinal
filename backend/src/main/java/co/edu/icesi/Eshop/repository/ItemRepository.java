package co.edu.icesi.Eshop.repository;

import co.edu.icesi.Eshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query("SELECT item FROM ITEM item WHERE item.name = :name")
    Optional<Item> findByName(String name);
}
