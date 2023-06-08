package co.com.icesi.eShop_Back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseCategoryDTO{
    private UUID id;
    private String name;
    private String description;
    private List<ResponseItemDTO> items;
}
