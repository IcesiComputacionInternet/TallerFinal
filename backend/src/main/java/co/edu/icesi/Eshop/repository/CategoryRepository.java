package co.edu.icesi.Eshop.repository;

import co.edu.icesi.Eshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT category FROM Category category WHERE  category.name= :name")
    Optional<Category> findByName(String name);
}
