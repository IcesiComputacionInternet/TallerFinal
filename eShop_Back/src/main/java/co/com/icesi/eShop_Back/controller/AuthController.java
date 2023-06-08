package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.dto.request.LoginDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseAuthDTO;
import co.com.icesi.eShop_Back.service.security.TokenService;
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

    @PostMapping("/auth")
    public ResponseAuthDTO login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
        return tokenService.generateToken(authentication);
    }
}
