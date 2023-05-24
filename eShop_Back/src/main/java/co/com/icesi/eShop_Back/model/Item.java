package co.com.icesi.eShop_Back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private UUID itemId;
    private String name;
    private String description;
    private Long price;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "category_category_id")
    private Category category;
    @ManyToMany(mappedBy = "items")
    private List<Order> orders;
}
