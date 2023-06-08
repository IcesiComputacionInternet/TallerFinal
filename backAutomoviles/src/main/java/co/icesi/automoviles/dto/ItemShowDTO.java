package co.icesi.automoviles.dto;


import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemShowDTO {
    private UUID itemId;
    private String description;
    private String name;
    private long price;
    private String imageUrl;
    private CategoryShowDTOForItem category;
}
