package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(UserApi.USER_BASE_URL)
public interface UserApi {
    String USER_BASE_URL = "/api/users";

    @PostMapping
    void save(@RequestBody @Valid RequestUserDTO userDTO);

    @GetMapping("/get/id/{id}")
    RequestUserDTO getById(@PathVariable("id") String id);

    @DeleteMapping("/delete/id/{id}")
    void deleteById(@PathVariable("id") String id);
}
