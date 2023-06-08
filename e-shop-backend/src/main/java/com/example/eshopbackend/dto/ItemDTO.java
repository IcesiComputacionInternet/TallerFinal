package com.example.eshopbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDTO {

    private String itemId;
    private String name;
    private String price;
    private String imageUrl;
    private String description;
}
