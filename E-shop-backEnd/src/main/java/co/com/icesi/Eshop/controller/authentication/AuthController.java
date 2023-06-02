package co.com.icesi.Eshop.controller.authentication;

import co.com.icesi.Eshop.dto.security.LoginDTO;
import co.com.icesi.Eshop.dto.security.JwtDTO;
import co.com.icesi.Eshop.service.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public JwtDTO token(@RequestBody @Valid LoginDTO loginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
        return tokenService.generateToken(authentication);
    }
}
