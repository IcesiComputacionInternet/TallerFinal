package co.com.icesi.eShopBackEnd.model;

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
    private String description;
    private String name;
    private Long price;
    private String imageURL;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_category_id")
    private Category category;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;

}
