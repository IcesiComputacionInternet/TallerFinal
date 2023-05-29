package co.com.icesi.eShopBackEnd.controller;

import co.com.icesi.eShopBackEnd.api.RoleAPI;
import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import co.com.icesi.eShopBackEnd.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RoleController implements RoleAPI {

    private final RoleService roleService;
    @Override
    public CreateRoleDTO createRole(CreateRoleDTO role) {
        return roleService.save(role);
    }
}
