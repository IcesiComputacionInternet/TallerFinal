package co.com.icesi.backend.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountRole {
    ADMIN("ADMIN"),
    SHOP("SHOP"),
    USER("USER");

    private final String role;
}
