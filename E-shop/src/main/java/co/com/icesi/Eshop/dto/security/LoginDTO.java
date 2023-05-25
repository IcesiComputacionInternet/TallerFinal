package co.com.icesi.Eshop.dto.security;

import lombok.Builder;

@Builder
public record LoginDTO(String username, String password) {
}
