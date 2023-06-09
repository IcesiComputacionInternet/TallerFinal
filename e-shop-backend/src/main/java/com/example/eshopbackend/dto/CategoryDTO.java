package com.example.eshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    private String categoryId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

}
