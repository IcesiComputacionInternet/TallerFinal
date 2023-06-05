package co.edu.icesi.Eshop.model;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne(optional = false,cascade= CascadeType.ALL)
    @JoinColumn(name="eshop_order_order_id", nullable = false)
    private EShopOrder order;
}
