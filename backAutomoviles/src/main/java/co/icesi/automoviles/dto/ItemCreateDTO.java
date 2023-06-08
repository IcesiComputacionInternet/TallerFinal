package co.icesi.automoviles.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemCreateDTO {
    private String description;
    private String name;
    private long price;
    private String imageUrl;
    private String categoryUUID;
}
