package com.example.eshopbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    private UUID roleId;

    private String roleName;

    private String description;

    @OneToMany(mappedBy = "userId")
    private List<User> users;
}
