package com.icesi.backend.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Column(nullable = false, unique = true)
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID itemId;
    private String description;
    private String name;
    private Long price;
    private String imgUrl;
    @ManyToOne
    private Category category;
    private boolean available;

    @PrePersist
    public void generateId() {
        this.itemId = UUID.randomUUID();
    }



}
