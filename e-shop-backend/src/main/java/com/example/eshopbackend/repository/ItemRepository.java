package com.example.eshopbackend.repository;

import com.example.eshopbackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query("SELECT i FROM Item i WHERE i.name = ?name")
    Optional<Item> findByName(String name);


    @Query("SELECT i FROM Item i WHERE i.category.categoryId = ?categoryId")
    Optional<Item> findByCategoryId(UUID categoryId);



}
