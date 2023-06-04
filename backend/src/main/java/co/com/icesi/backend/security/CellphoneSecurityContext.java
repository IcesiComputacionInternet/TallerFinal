package co.com.icesi.backend.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

public class CellphoneSecurityContext {
    public static UUID getCurrentUserId(){
        return UUID.fromString(((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
                .getToken().getClaimAsString("userId"));
    }
}
