package com.icesi.backend.models;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Data
@Table(name = "role")
public class Role {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    private String roleName;
    private String description;
}
