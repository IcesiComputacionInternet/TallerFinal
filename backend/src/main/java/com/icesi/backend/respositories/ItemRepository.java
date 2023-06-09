package com.icesi.backend.respositories;

import com.icesi.backend.models.Category;
import com.icesi.backend.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findByAvailableAndItem(boolean available, UUID itemId);

    @Transactional
    @Modifying
    @Query("update Item i SET i.description = ?1, i.name = ?2, i.price = ?3, i.imgUrl = ?4, i.category = ?5, i.available = ?6 WHERE i.itemId = ?7")
    void updateItemAvailabilityByItemId(String description, String name, long price, String imgUrl, Category category, boolean available, UUID itemId);

}
