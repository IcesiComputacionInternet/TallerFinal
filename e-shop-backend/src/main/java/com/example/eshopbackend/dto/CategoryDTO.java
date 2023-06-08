package com.example.eshopbackend.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class CategoryDTO {
    @NotBlank
    private String categoryId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

}
