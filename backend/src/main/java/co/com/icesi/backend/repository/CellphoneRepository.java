package co.com.icesi.backend.repository;

import co.com.icesi.backend.model.Cellphone;
import co.com.icesi.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CellphoneRepository extends JpaRepository<Cellphone, UUID> {
    @Query("SELECT cellphone FROM Cellphone cellphone WHERE cellphone.name = :name")
    Optional<Cellphone> findByName(@Param("name") String name);
}
