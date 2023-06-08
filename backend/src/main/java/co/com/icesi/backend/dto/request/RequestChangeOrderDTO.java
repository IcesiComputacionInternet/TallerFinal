package co.com.icesi.backend.dto.request;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestChangeOrderDTO {

    private UUID orderId;

    private String newStatus;
}
