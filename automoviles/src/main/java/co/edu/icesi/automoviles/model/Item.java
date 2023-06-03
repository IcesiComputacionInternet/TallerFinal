package co.edu.icesi.automoviles.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private UUID itemId;
    private String description;
    private String name;
    private long price;
    private String imageUrl; 
    //private Category category;
}
