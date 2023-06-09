package com.example.eshopbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "roles")
public class Role {
    @Id
    private UUID roleId;

    private String roleName;

    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
