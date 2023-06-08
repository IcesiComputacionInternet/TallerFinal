package com.icesi.backend.models;

import java.util.UUID;

public class Item {
    private UUID itemId;
    private String description;
    private String name;
    private Long price;
    private String imgUrl;
    private Category category;
}
