package co.edu.icesi.automoviles.model;

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
public class Category {
    @Id
    private UUID categoryId;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Item> items;
}
