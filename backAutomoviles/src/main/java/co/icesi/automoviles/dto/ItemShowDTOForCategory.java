package co.icesi.automoviles.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ItemShowDTOForCategory {
    private UUID itemId;
    private String description;
    private String name;
    private long price;
    private String imageUrl;
}
