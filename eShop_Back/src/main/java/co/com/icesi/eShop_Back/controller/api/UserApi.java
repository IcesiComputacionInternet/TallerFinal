package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(UserApi.USER_BASE_URL)
public interface UserApi {
    String USER_BASE_URL = "/api/v1/users";

    @PostMapping
    void saveUser(@RequestBody @Valid RequestUserDTO userDTO);

    @GetMapping("/get/id/{id}")
    RequestUserDTO getUserById(@PathVariable("id") String id);

    @DeleteMapping("/delete/id/{id}")
    void deleteUserById(@PathVariable("id") String id);
}
