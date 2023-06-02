package co.com.icesi.eShopBackEnd.api;

import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.response.RoleDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static co.com.icesi.eShopBackEnd.api.CustomerAPI.BASE_USER_URL;


@RequestMapping(value = BASE_USER_URL)
public interface CustomerAPI {
    String BASE_USER_URL = "/user";

    @PostMapping
    ResponseCustomerDTO createUser(@Valid @RequestBody CreateCustomerDTO user);

    @GetMapping("/role")
    RoleDTO getRoleOfUser();
}
