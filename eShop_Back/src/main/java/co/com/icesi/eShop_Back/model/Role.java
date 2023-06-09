package co.com.icesi.eShop_Back.model;

import co.com.icesi.eShop_Back.model.security.UserPermission;
import lombok.*;

import javax.persistence.*;
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
    private String name;
    private String description;
    @OneToMany(mappedBy = "role")
    private List<User> users;
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserPermission> permissionList;
}
