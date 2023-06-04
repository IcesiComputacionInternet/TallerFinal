package co.com.icesi.backend.repository;

import co.com.icesi.backend.model.Category;
import co.com.icesi.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT category FROM Category category WHERE category.name = :name")
    Optional<Category> findByName(@Param("name") String name);
}
