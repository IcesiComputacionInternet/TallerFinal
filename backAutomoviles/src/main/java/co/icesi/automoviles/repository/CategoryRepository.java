package co.icesi.automoviles.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.icesi.automoviles.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>{
    Optional<Category> findByName(String categoryName);
    @Query( "SELECT c " +
            "FROM Category c")
    Page<Category> getAllCategories(Pageable pageable);

    @Query( "SELECT c " +
            "FROM Category c")
    List<Category> getAllCategories();
}
