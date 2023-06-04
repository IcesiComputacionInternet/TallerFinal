package com.example.eshopbackend.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private UUID userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private String address;

    private LocalDateTime birthDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "roleId",nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
