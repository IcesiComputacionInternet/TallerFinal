package co.com.icesi.backend.dto.response;

import co.com.icesi.backend.dto.request.CategoryDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseCellphoneDTO {
    private CategoryDTO category;
    private String description;
    private String name;
    private long price;
    private String imageUrl;
}
