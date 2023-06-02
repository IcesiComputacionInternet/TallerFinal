package co.com.icesi.eShopBackEnd.repository;

import co.com.icesi.eShopBackEnd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value = "SELECT CASE WHEN (COUNT(*) > 0 )THEN true ELSE false END FROM Category c WHERE c.name = :name")
    boolean categoryExists(String name);

    @Query(value = "SELECT c FROM Category c WHERE c.name = :name")
    Optional<Category> returnCategory(String name);
}
