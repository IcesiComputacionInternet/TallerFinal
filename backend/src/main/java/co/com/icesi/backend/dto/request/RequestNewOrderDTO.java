package co.com.icesi.backend.dto.request;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestNewOrderDTO {
    private List<RequestOrderItemDTO> items;
    private long total;
}

