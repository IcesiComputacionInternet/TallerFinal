package co.com.icesi.Eshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotBlank
    @NotNull
    private String userEmail;
    @NotBlank
    @NotNull
    private String status;

    @NotBlank
    @NotNull
    private Long total;


    @NotNull
    private List<String> items;

}
