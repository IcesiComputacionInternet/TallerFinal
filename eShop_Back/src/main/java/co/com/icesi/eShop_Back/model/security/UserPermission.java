package co.com.icesi.eShop_Back.model.security;

import co.com.icesi.eShop_Back.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserPermission {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "pg-uuid")
    private UUID permissionId;
    private String path;
    @Column(name ="`key`")
    private String key;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_permission_roles",
            joinColumns = @JoinColumn(name = "user_permission_permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_role_id")
    )
    private List<Role> roles;

}