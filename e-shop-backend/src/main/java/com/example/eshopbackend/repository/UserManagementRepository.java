package com.example.eshopbackend.repository;

import com.example.eshopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserManagementRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
