package co.edu.icesi.automoviles.dto;

import java.util.UUID;
import java.util.List;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class CategoryShowDTO {
    private UUID categoryId;
    private String name;
    private String description;
    private List<ItemShowDTO> items;
    
}
