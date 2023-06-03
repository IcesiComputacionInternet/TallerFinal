package co.edu.icesi.automoviles.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.automoviles.api.RoleAPI;
import co.edu.icesi.automoviles.dto.RoleCreateDTO;
import co.edu.icesi.automoviles.dto.RoleShowDTO;
import co.edu.icesi.automoviles.service.RoleService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class RoleController implements RoleAPI{

    private final RoleService roleService;
    
    @Override
    public RoleShowDTO registerRole(@Valid RoleCreateDTO roleCreateDTO) {
        return roleService.registerRole(roleCreateDTO);
    }
    
}
