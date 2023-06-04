package co.com.icesi.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Role {
    @OneToMany(mappedBy = "role")
    private List<User> users;
    @Id
    private UUID roleId;
    private String roleName;
    private String description;
}
