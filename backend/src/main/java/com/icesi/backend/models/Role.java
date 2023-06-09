package com.icesi.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Column(nullable = false, unique = true)
    private String roleName;
    private String description;

    @ManyToMany(cascade =
            {
            CascadeType.PERSIST,
            CascadeType.MERGE
            }
    , fetch = FetchType.EAGER)
    @JoinTable(name =
            "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<PermissionUser> rolePermissions;
}
