package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.RequestUserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static co.com.icesi.eShop_Back.controller.api.UserApi.BASE_URL;

@RequestMapping(BASE_URL)
public interface UserApi {
    String BASE_URL = "/api/v1/users";

    @PostMapping
    void add(@Valid @RequestBody RequestUserDTO user);
}
