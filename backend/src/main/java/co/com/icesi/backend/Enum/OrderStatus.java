package co.com.icesi.backend.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    IN_PROCESS("IN_PROCESS"),
    SENT("SENT"),
    DELIVERED("DELIVERED");
    private final String status;

    public static boolean isStringEqualToEnum(String input) {
        try {
            OrderStatus myEnumValue = OrderStatus.valueOf(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
