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
    private String user;
    private String status;
    private long total;
    private List<String> items;
    private String message;
}
