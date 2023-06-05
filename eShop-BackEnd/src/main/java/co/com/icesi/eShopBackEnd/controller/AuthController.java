package co.com.icesi.eShopBackEnd.controller;

import co.com.icesi.eShopBackEnd.dto.LoginDTO;
import co.com.icesi.eShopBackEnd.dto.response.TokenDTO;
import co.com.icesi.eShopBackEnd.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public TokenDTO token(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(),loginDTO.password()));
        return tokenService.generateToken(authentication);
    }



}
