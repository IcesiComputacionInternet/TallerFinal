package co.icesi.automoviles.model;

import java.util.UUID;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "role")
    private List<EShopUser> eShopUsers;
}
