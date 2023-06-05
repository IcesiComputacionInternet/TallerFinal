package co.edu.icesi.Eshop.model;

import lombok.*;

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

    private String imageUrl;

    @ManyToOne(optional = false,cascade= CascadeType.ALL)
    @JoinColumn(name="category_category_id", nullable = false)
    private Category category;

    private double minVoltage;

    private double maxVoltage;

    private String sourceOfEnergy;

    private String levelOfEfficiency;

    private String marca;

    private String model;

    private int guarantee;

    private boolean available;

    @ManyToMany
    @JoinTable(
            name = "items_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<EShopOrder> orders;
}
