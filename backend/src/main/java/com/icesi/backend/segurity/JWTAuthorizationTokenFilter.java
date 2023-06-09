
package com.icesi.backend.segurity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.backend.error.exception.E_SHOP_Error;
import com.icesi.backend.error.exception.E_SHOP_Exception;
import com.icesi.backend.errorConstants.BackendApplicationErrors;
import com.icesi.backend.service.LoginService;
import com.icesi.backend.service.TOken_Parser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Permission;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
@AllArgsConstructor
@Order(1)
public class JWTAuthorizationTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private static final String USER_ID_CLAIM_NAME = "userId";

    private static final String ROLE_ID_CLAIM_NAME = "roleId";

    private static final String[] excludedPaths = {"POST /users", "POST /login","OPTIONS /users", "OPTIONS /login", "OPTIONS /items", "OPTIONS /orders"};

    private final LoginService loginService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (containsToken(request)) {
                String jwtToken = request.getHeader(AUTHORIZATION_HEADER).replace(TOKEN_PREFIX, StringUtils.EMPTY);
                Claims claims = TOken_Parser.decodeJWT(jwtToken);
                SecurityContext context = parseClaims(jwtToken, claims);
                SecurityContextHolder.setUserContext(context);
                roleFilter(context, request, response);
                filterChain.doFilter(request, response);
            } else {
                createUnauthorizedFilter(new E_SHOP_Exception(HttpStatus.UNAUTHORIZED, new E_SHOP_Error(BackendApplicationErrors.CODE_L_03, BackendApplicationErrors.CODE_L_03.getMessage())), response);
            }
        } catch (JwtException e) {
            System.out.println("Error verifying JWT token: " + e.getMessage());
            createUnauthorizedFilter(new E_SHOP_Exception(HttpStatus.UNAUTHORIZED, new E_SHOP_Error(BackendApplicationErrors.CODE_L_03, BackendApplicationErrors.CODE_L_03.getMessage())), response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private void roleFilter(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

        List<Permission> permissions = loginService.getPermissionsByRoleId(context.getRoleId());

        boolean isValid = false;

        for (Permission p : permissions) {
            if (p.getMethod().equals(request.getMethod()) && request.getRequestURI().startsWith(p.getUri())) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            createUnauthorizedFilter(new E_SHOP_Exception(HttpStatus.UNAUTHORIZED, new E_SHOP_Error(BackendApplicationErrors.CODE_L_03, BackendApplicationErrors.CODE_L_03.getMessage())), response);
        }
    }

    private SecurityContext parseClaims(String jwtToken, Claims claims) {
        String userId = claimKey(claims, USER_ID_CLAIM_NAME);
        String roleId = claimKey(claims, ROLE_ID_CLAIM_NAME);

        SecurityContext context = new SecurityContext();
        try {
            context.setUserId(UUID.fromString(userId));
            context.setRoleId(UUID.fromString(roleId));
            context.setToken(jwtToken);
        } catch (IllegalArgumentException e) {
            throw new MalformedJwtException("Error parsing jwt");
        }
        return context;
    }

    private String claimKey(Claims claims, String key) {
        String value = (String) claims.get(key);
        return Optional.ofNullable(value).orElseThrow();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String methodPlusPath = request.getMethod() + " " + request.getRequestURI();
        return Arrays.stream(excludedPaths).anyMatch(path -> path.equalsIgnoreCase(methodPlusPath));
    }

    private boolean containsToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(AUTHORIZATION_HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_PREFIX);
    }

    @SneakyThrows
    private void createUnauthorizedFilter(E_SHOP_Exception ESHOP_Exception, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();

        E_SHOP_Error ESHOP_Error = ESHOP_Exception.getError();

        String message = objectMapper.writeValueAsString(ESHOP_Error);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        response.getWriter().write(message);
        response.getWriter().flush();
    }

}
