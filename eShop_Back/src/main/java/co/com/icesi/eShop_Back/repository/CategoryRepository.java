package co.com.icesi.eShop_Back.repository;

import co.com.icesi.eShop_Back.model.Category;
import co.com.icesi.eShop_Back.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT r FROM Category r WHERE r.name = :name")
    Optional<Category> findByName(@Param("name") String name);

    @Query("SELECT CASE WHEN (COUNT(u) > 0) THEN true ELSE false END FROM Category u WHERE u.name = :name")
    boolean existsByName(@Param("name") String name);
}
