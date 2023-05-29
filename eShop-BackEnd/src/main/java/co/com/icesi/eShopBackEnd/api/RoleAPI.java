package co.com.icesi.eShopBackEnd.api;

import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static co.com.icesi.eShopBackEnd.api.RoleAPI.BASE_ROLE_URL;

@RequestMapping(value = BASE_ROLE_URL)
public interface RoleAPI {
    String BASE_ROLE_URL = "/role";

    @PostMapping
    CreateRoleDTO createRole(@Valid @RequestBody CreateRoleDTO role);
}
