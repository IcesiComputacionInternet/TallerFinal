package com.peliculas.grupo3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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

    @ManyToMany(mappedBy = "movies")
    private List<MovieOrder> oders;

}
