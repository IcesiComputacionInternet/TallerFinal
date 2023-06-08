package com.example.eshopbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDTO {

    private String categoryId;
    private String name;
    private String description;

}
