package com.example.eshopbackend.enums;

import com.example.eshopbackend.model.Role;
import lombok.Getter;

@Getter
public enum TypeRole {
    ADMIN("ADMIN"),
    USER("USER"),
    SHOP("SHOP");

    private final String role;
    private  TypeRole (String typeRole) {
        role = typeRole;
    }
}
