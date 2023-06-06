package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.dto.response.UserResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.peliculas.grupo3.api.UserApi.BASE_USER_URL;

@RequestMapping(value = BASE_USER_URL)
public interface UserApi {
    String BASE_USER_URL = "/users";

    //TODO crear los metodos para el crud de usuarios


    @PostMapping("/")
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @GetMapping("/all")
    List<UserResponseDTO> getAllUser();

    @GetMapping("/findByName")
    Optional<UserResponseDTO> findByName(@RequestBody String name);

    @CrossOrigin
    @GetMapping("/CurrentUser")
    UserResponseDTO getCurrentUser();
}
