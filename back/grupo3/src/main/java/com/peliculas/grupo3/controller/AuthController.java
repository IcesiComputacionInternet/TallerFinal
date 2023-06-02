package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.dto.LoginDTO;
import com.peliculas.grupo3.dto.TokenDTO;
import com.peliculas.grupo3.security.SecurityContext;
import com.peliculas.grupo3.service.TokenSercive;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {
    private final TokenSercive tokenSercive;

    private final AuthenticationManager authenticationManager;

    @CrossOrigin
    @PostMapping("/token")
    public TokenDTO token(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
        return tokenSercive.generateToken(authentication);
    }

    @GetMapping("/token/scope")
    public String tokenScope() {
    	return SecurityContext.getCurrentUserRole();
    }

    @GetMapping("/token/id")
    public String tokenId() {
        return SecurityContext.getCurrentUserId();
    }
}
