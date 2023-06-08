package com.icesi.backend.controllers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public Optional<ShopUser> createUser(@RequestBody UserCreateDTO userCreateDTO){
        return userService.createUser(userCreateDTO);
    }
}
