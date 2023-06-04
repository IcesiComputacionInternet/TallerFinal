package co.icesi.automoviles.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.icesi.automoviles.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>{
    Optional<Category> findByName(String categoryName);
}
