package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.dto.LoginDTO;
import co.edu.icesi.Eshop.dto.TokenDTO;
import co.edu.icesi.Eshop.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticatorManager;

    @PostMapping("/token")
    public TokenDTO token(@RequestBody LoginDTO loginDTO){

        Authentication authentication;

        if (loginDTO.username() == null){
            authentication = authenticatorManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.phoneNumber(), loginDTO.password()));

        }else{
            authentication = authenticatorManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
        }

        return tokenService.generateToken(authentication);
    }

}
