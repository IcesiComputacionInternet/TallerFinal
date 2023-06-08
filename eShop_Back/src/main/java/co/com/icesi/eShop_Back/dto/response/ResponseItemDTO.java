package co.com.icesi.eShop_Back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItemDTO{
    UUID itemId;
    String name;
    String description;
    Long price;
    String imageUrl;
    String category;
}
