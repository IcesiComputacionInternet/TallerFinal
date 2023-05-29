package co.com.icesi.eShopBackEnd.api;

import co.com.icesi.eShopBackEnd.dto.CreateUserDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseUserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static co.com.icesi.eShopBackEnd.api.RoleAPI.BASE_ROLE_URL;

@RequestMapping(value = BASE_ROLE_URL)
public interface UserAPI {
    String BASE_ROLE_URL = "/user";

    @PostMapping
    ResponseUserDTO createIcesiUser(@Valid @RequestBody CreateUserDTO user);
}
