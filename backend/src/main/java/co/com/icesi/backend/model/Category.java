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

public class Category {
    @OneToMany(mappedBy = "category")
    private List<Cellphone> cellphones;
    @Id
    private UUID categoryId;
    private String name;
    private String description;
}
