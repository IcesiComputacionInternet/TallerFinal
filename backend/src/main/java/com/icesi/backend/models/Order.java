package com.icesi.backend.models;

import java.util.List;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private User user;
    private String status;
    private Long total;
    private List<Item> itemList;
}
