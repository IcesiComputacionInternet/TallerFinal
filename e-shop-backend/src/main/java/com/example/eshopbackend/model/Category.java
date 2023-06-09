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
public class Category {
    @Id
    private UUID categoryId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Item> items;
}
