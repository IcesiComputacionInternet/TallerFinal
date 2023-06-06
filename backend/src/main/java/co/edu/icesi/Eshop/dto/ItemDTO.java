package co.edu.icesi.Eshop.dto;

import co.edu.icesi.Eshop.validation.constraint.LevelOfEfficiencyConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    @NotBlank(message = "is missing")
    private String name;

    @NotBlank(message = "is missing")
    private String description;

    @Min(value=1L, message = "min value is 1")
    @Max(value=1000000000000L, message = "max value is 1000000000000")
    private Long price;

    @NotBlank(message = "is missing")
    private String imageUrl;

    @NotBlank(message = "is missing")
    private String category;

    @Min(value=0, message = "min value is 0")
    private double minVoltage;

    @Min(value=0, message = "min value is 0")
    private double maxVoltage;

    @NotBlank(message = "is missing")
    private String sourceOfEnergy;

    @NotBlank(message = "is missing")
    @LevelOfEfficiencyConstraint
    private String levelOfEfficiency;

    @NotBlank(message = "is missing")
    private String marca;

    @NotBlank(message = "is missing")
    private String model;

    @Min(value=0, message = "min value is 0")
    private int guarantee;

    private boolean available;
}
