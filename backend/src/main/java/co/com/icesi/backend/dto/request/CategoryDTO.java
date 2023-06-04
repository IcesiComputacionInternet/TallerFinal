package co.com.icesi.backend.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotNull(message = "The name of a category can't be null")
    @NotBlank(message = "The name of a category can't be blank")
    private String name;
    @NotBlank(message = "The description of a category can't be blank")
    private String description;
}
