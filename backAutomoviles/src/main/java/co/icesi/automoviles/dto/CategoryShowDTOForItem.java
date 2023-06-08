package co.icesi.automoviles.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CategoryShowDTOForItem {
    private UUID categoryId;
    private String name;
    private String description;
}
