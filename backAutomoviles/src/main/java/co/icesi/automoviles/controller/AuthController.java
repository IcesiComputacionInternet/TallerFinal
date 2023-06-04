package co.icesi.automoviles.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.automoviles.dto.LoginDTO;
import co.icesi.automoviles.dto.TokenDTO;
import co.icesi.automoviles.service.TokenService;


@RestController
@AllArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    
    @PostMapping("/token")
    public TokenDTO token(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(),loginDTO.password()));
        return tokenService.generateToken(authentication);
    }
}
