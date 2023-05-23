package co.com.icesi.eShop_Back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private UUID orderId;
    private String name;
    private String description;
    private Long price;
    private String imageUrl;
    @OneToOne
    private Category category;
}
