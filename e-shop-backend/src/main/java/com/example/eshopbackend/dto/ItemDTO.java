package com.example.eshopbackend.dto;

import com.example.eshopbackend.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private String itemId;
    @NotBlank
    private String name;
    @NotBlank
    @Min(value = 1, message = "El precio debe ser mayor que 0"  )
    private long price;
    private String imageUrl;
    @NotBlank
    private String description;
    private CategoryDTO category;

    private int warranty;
}
