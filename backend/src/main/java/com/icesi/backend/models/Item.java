package com.icesi.backend.models;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Data
@Table(name = "item")
public class Item {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID itemId;
    private String description;
    private String name;
    private Long price;
    private String imgUrl;
    @ManyToOne
    private Category category;
}
