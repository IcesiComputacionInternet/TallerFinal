package com.peliculas.grupo3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ORDERMV")
public class MovieOrder {

    @Id
    private UUID orderId;

    private String status;

    private long total;

    @ManyToOne
    @JoinColumn(name="usermv_user_id", nullable=false)
    private MovieUser user;

    @ManyToMany
    @JoinTable(
            name = "ordermv_movie",
            joinColumns = @JoinColumn(name = "ordermv_order_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_movie_id"))
    private List<Movie> movies;

}
