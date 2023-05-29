package co.com.icesi.eShopBackEnd.controller;

import co.com.icesi.eShopBackEnd.api.UserAPI;
import co.com.icesi.eShopBackEnd.dto.CreateUserDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseUserDTO;
import co.com.icesi.eShopBackEnd.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;
    @Override
    public ResponseUserDTO createIcesiUser(CreateUserDTO user) {
        return userService.save(user);
    }
}
