package com.example.eshopbackend.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@Data
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
    @NotBlank
    private CategoryDTO category;

    private int warranty;
}
