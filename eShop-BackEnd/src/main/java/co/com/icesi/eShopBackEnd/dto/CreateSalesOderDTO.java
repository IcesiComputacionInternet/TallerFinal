package co.com.icesi.eShopBackEnd.dto;

import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
public record CreateSalesOderDTO(

        @NotBlank
        String customer,


        List<CreateItemDTO> items

) {
}
