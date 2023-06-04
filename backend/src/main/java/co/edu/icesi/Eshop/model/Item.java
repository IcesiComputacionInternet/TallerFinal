package co.edu.icesi.Eshop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
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

    private Category category;

    private double minVoltage;

    private double maxVoltage;

    private String sourceOfEnergy;

    private String levelOfEfficiency;

    private String marca;

    private String model;

    private int guarantee;

    private boolean available;
}
