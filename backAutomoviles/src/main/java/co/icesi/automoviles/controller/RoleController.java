package co.icesi.automoviles.controller;

import org.springframework.web.bind.annotation.RestController;

import co.icesi.automoviles.api.RoleAPI;
import co.icesi.automoviles.dto.RoleCreateDTO;
import co.icesi.automoviles.dto.RoleShowDTO;
import co.icesi.automoviles.service.RoleService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RoleController implements RoleAPI{

    private final RoleService roleService;
    
    @Override
    public RoleShowDTO registerRole(RoleCreateDTO roleCreateDTO) {
        return roleService.registerRole(roleCreateDTO);
    }
    
}
