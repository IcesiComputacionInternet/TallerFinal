package co.edu.icesi.Eshop.repository;

import co.edu.icesi.Eshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query("SELECT item FROM Item item WHERE item.name = :name")
    Optional<Item> findByName(@Param("name") String name);
}
