package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.RoleAPI;
import co.edu.icesi.Eshop.dto.RoleDTO;
import co.edu.icesi.Eshop.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.Eshop.api.RoleAPI.BASE_ROLE_URL;

@RequestMapping(BASE_ROLE_URL)
@RestController
@AllArgsConstructor
@CrossOrigin
public class RoleController implements RoleAPI {

    private final RoleService roleService;

    @Override
    public RoleDTO getRoleByName(String name) {
        return roleService.getRoleByName(name);
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }
}
