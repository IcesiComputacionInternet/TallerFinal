package co.com.icesi.eShopBackEnd.repository;

import co.com.icesi.eShopBackEnd.model.Category;
import co.com.icesi.eShopBackEnd.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query(value = "SELECT CASE WHEN (COUNT(*) > 0 )THEN true ELSE false END FROM Item i WHERE i.name = :name")
    boolean itemExists(String name);

    @Query(value = "SELECT i FROM Item i where i.name = :itemName")
    Optional<Item> returnItem(String itemName);


    @Modifying
    @Query(value = "UPDATE Item i SET i.category = :category WHERE i.name = :itemName")
    void updateCategory(String itemName, Category category);
}
