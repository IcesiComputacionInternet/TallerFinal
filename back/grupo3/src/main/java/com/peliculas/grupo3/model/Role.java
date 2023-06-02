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
@Table(name="ROLE")
public class Role {

    @Id
    private UUID roleId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "role")
    private List<MovieUser> movieUser;

}