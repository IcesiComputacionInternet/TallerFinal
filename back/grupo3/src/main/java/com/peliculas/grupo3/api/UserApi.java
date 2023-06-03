package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.peliculas.grupo3.api.UserApi.BASE_USER_URL;

@RequestMapping(value = BASE_USER_URL)
public interface UserApi {
    String BASE_USER_URL = "/users";

    //TODO crear los metodos para el crud de usuarios


    @PostMapping("/")
    UserDTO createUser(@RequestBody UserDTO userDTO);
}
