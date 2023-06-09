package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.dto.response.UserResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import static com.peliculas.grupo3.api.UserApi.BASE_USER_URL;

@RequestMapping(value = BASE_USER_URL)
public interface UserApi {
    String BASE_USER_URL = "/users";


    @PostMapping("/")
    UserDTO createUser(@Valid @RequestBody UserDTO userDTO);

    @GetMapping("/all")
    List<UserResponseDTO> getAllUser();

    @GetMapping("/findByName/{name}")
    UserResponseDTO findByName(@PathVariable String name);

    @CrossOrigin
    @GetMapping("/CurrentUser")
    UserResponseDTO getCurrentUser();
}
