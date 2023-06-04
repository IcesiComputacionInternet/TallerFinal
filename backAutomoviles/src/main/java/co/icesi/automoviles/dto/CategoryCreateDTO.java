package co.icesi.automoviles.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryCreateDTO {
    private String name;
    private String description;
}
