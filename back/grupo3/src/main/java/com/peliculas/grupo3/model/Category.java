package com.peliculas.grupo3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CATEGORY")
public class Category {

    @Id
    private UUID categoryId;

    private String name;

    private String description;

    private String pgRating;


}
