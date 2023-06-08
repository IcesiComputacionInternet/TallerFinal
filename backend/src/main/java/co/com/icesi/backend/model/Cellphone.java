package co.com.icesi.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cellphone {
    @Id
    private UUID cellphoneId;
    private String name;
    private String description;
    private Long price;
    private String imageUrl;
    private String brand;
    private String storage;
    private String ram;
    private String operatingSystem;
    private String frontCameraResolution;
    private String mainCameraResolution;
    private String screenSize;
    private int stock;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_category_id", nullable = false)
    private Category category;
    @ManyToMany(mappedBy = "items")
    private List<ShopOrder> shopOrders;
}
