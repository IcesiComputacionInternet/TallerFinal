package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.api.UserApi;

import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController implements UserApi {
    //TODO implementar metodos de la api

    private final UserService userService;

    @Override
    public UserDTO createUser(UserDTO user){
        return userService.save(user);
    }
}
