package com.example.eshopbackend.controller;

import com.example.eshopbackend.api.UserApi;
import com.example.eshopbackend.dto.UserDTO;
import com.example.eshopbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    @Override
    public UserDTO createUser(UserDTO user) {
        return userService.save(user);
    }

    @Override
    public UserDTO getById(String id) {
        return userService.get(UUID.fromString(id));
    }

    @Override
    public void deleteById(String id) {
        userService.delete(UUID.fromString(id));
    }

    @Override
    public void update(UserDTO userDTO, String id) {
        userService.update(userDTO, UUID.fromString(id));
    }
}
