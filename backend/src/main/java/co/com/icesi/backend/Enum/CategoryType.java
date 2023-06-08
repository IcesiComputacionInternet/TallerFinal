package co.com.icesi.backend.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryType {
    HIGH_RANGE("HIGH_RANGE"),
    MID_RANGE("MID_RANGE"),
    LOW_RANGE("LOW_RANGE");

    private final String category;
}
