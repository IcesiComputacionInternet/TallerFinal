package co.com.icesi.eShop_Back.controller.api;

import co.com.icesi.eShop_Back.dto.request.RoleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(RoleApi.ROLE_BASE_URL)
public interface RoleApi {
    String ROLE_BASE_URL = "/api/v1/roles";

    @PostMapping
    void saveRole(@RequestBody RoleDTO roleDTO);
}
