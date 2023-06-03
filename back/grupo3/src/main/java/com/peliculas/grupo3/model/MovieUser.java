package com.peliculas.grupo3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USERMV")
public class MovieUser {
    @Id
    private UUID userId;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private LocalDateTime birthDate;
    private String phone;

    private String password;


    @ManyToOne
    @JoinColumn(name="role_role_id", nullable=false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<MovieOrder> movieOrders;

}

