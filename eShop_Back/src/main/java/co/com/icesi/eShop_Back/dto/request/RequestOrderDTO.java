package co.com.icesi.eShop_Back.dto.request;

import co.com.icesi.eShop_Back.enums.Status;
import co.com.icesi.eShop_Back.validation.list.AmountList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {
    @NotBlank(message = "User id is mandatory")
    private String user;
    @NotNull(message = "Total is mandatory")
    @Min(value = 0, message = "Total must be greater than 0")
    private Long total;
    @NotNull(message = "Items is mandatory")
    //@AmountList(message = "Items must not be empty")
    @Size(min = 1, message = "Items must not be empty")
    private List<String> items;

}
