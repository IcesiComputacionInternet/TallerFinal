package co.edu.icesi.Eshop.model;

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

    @Id
    private UUID roleId;

    private String roleName;

    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
