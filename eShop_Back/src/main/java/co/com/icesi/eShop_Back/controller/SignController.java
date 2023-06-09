package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.dto.request.LoginDTO;
import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseAuthDTO;
import co.com.icesi.eShop_Back.service.UserService;
import co.com.icesi.eShop_Back.service.security.TokenService;
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
public class SignController {

    private final TokenService tokenService;
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseAuthDTO login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
        return tokenService.generateToken(authentication);
    }
    @PostMapping("/register")
    public void saveClient(@RequestBody @Valid RequestUserDTO userDTO) {
        userService.create(userDTO, "CLIENT");
    }
}
