package com.example.eshopbackend.api;

import com.example.eshopbackend.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(UserApi.USER_BASE_URL)
public interface UserApi {
    String USER_BASE_URL = "/users";

    @PostMapping("/add")
    UserDTO createUser(@RequestBody @Valid UserDTO user);

    @GetMapping("get/{id}")
    UserDTO getById(@PathVariable("id") String id);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable("id") String id);

    @PostMapping("/update/{id}")
    void update(@RequestBody @Valid UserDTO userDTO, @PathVariable("id") String id);
}
