package com.example.eshopbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Item {
    @Id
    private UUID itemId;
    private String name;

    private String description;

    private String imageUrl;

    private long price;


    private int warranty;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "category_category_id",nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;
}
