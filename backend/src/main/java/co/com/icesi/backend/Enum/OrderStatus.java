package co.com.icesi.backend.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATING("CREATING"),
    IN_PROCESS("IN_PROCESS"),
    SENT("SENT"),
    DELIVERED("DELIVERED");
    private final String status;
}
