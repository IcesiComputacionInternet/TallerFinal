package co.edu.icesi.automoviles.repository;

import java.util.Optional;

import co.edu.icesi.automoviles.model.Role;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}
