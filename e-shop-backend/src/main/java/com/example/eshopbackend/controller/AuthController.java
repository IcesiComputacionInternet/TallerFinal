package com.example.eshopbackend.controller;

import com.example.eshopbackend.dto.LoginDTO;
import com.example.eshopbackend.dto.TokenDTO;
import com.example.eshopbackend.service.TokenService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @CrossOrigin(origins="http://localhost:5173")
    @PostMapping("/token")
    public TokenDTO token(@RequestBody LoginDTO loginDto){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(),loginDto.password()));
        //return tokenService.generateToken(authentication);
        return TokenDTO.builder().token(tokenService.generateToken(authentication)).build();
    }
}
