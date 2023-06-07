package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.api.UserApi;

import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.dto.response.UserResponseDTO;
import com.peliculas.grupo3.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController implements UserApi {
    //TODO implementar metodos de la api

    private final UserService userService;

    @Override
    public UserDTO createUser(UserDTO user){
        return userService.save(user);
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        return userService.findAll();
    }

    @Override
    public UserResponseDTO findByName(String name) {
        return userService.findByName(name);
    }

    @Override
    public UserResponseDTO getCurrentUser() {
        return userService.getCurrentUser();
    }


}
