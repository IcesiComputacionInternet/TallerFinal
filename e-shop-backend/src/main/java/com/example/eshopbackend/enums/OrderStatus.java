package com.example.eshopbackend.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PROCESS("IN PROCESS"),
    DELIVERED("DELIVERED"),
    FINISHED("FINISHED");

    private final String status;
    private  OrderStatus (String orderStatus) {
        status = orderStatus;
    }
}
