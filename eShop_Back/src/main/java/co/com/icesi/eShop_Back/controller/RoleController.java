package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.controller.api.RoleApi;
import co.com.icesi.eShop_Back.dto.request.RoleDTO;
import co.com.icesi.eShop_Back.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RoleController implements RoleApi {
    private final RoleService roleService;
    @Override
    public void saveRole(RoleDTO roleDTO) {
        roleService.create(roleDTO);
    }
}
