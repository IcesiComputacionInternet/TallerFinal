package com.example.eshopbackend.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("PENDIENTE"),
    PROCESS("EN CURSO"),
    FINISHED("TERMINADA");

    private final String status;
    private  OrderStatus (String orderStatus) {
        status = orderStatus;
    }
}
