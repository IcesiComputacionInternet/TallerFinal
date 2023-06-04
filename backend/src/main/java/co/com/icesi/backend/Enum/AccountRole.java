package co.com.icesi.backend.Enum;

public enum AccountRole {
    ADMIN("ADMIN"),
    SHOP("SHOP"),
    USER("USER");

    private final String role;

    private AccountRole(String role) {
        this.role = role;
    }

    private String getRole(){
        return this.role;
    }
}
