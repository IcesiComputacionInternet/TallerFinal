package co.com.icesi.eShopBackEnd.dto.response;

import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItemDTO {

    private UUID itemId;
    private String name;
    private Long price;
    private CreateCategoryDTO category;
    private String imageURL;
    private String description;
}
