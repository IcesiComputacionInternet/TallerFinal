package co.com.icesi.eShopBackEnd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    private UUID roleId;
    private String roleName;
    private String description;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private List<User> users;
}
