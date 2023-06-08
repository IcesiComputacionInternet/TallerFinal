package com.example.eshopbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoryId",nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;
}
