package co.edu.icesi.Eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private String name;

    private String description;

    private Long price;

    private String imageUrl;

    private String category;

    private double minVoltage;

    private double maxVoltage;

    private String sourceOfEnergy;

    private String levelOfEfficiency;

    private String marca;

    private String model;

    private int guarantee;

    private boolean available;
}
