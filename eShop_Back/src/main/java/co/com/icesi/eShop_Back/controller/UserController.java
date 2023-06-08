package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.controller.api.UserApi;
import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import co.com.icesi.eShop_Back.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public void saveUser(RequestUserDTO userDTO) {
        userService.create(userDTO);
    }

    @Override
    public RequestUserDTO getUserById(String id) {
        return userService.get(UUID.fromString(id));
    }

    @Override
    public void deleteUserById(String id) {
        userService.delete(UUID.fromString(id));
    }
}
