package com.peliculas.grupo3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MOVIE")
public class Movie {

    @Id
    private UUID movieId;

    private String name;

    private String description;

    private long price;

    private String imageURL;

    private String pgRating;

    @ManyToOne
    @JoinColumn(name="category_category_id", nullable=false)
    private Category category;


    @ManyToMany(mappedBy = "movies")
    private List<MovieOrder> oders;

}
