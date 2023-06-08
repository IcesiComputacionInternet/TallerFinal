package co.com.icesi.backend.dto.response;

import co.com.icesi.backend.dto.request.CategoryDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseOrderDTO {
    private CategoryDTO category;
    private String shopUser;
    private String status;
    private long total;
    private List<ResponseCellphoneDTO> items;
    private List<Integer> quantities;
    private String message;
}
