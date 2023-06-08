package com.icesi.backend.DTO;

import com.icesi.backend.models.Category;

import javax.persistence.ManyToOne;
import java.util.UUID;

public class ItemCreateDTO {
    private String description;
    private String name;
    private Long price;
    private String imgUrl;
}
