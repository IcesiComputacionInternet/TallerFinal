package com.icesi.backend.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Data
public class ShopUser {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String address;
    private LocalDateTime birthday;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    /*
    @OneToMany()
    private List<Order> orders;
*/
}
