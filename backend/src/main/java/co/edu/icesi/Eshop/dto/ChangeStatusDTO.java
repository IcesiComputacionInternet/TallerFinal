package co.edu.icesi.Eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusDTO {

    @NotBlank(message = "is missing")
    private String orderId;
    @NotBlank(message = "is missing")
    private String status;
}
