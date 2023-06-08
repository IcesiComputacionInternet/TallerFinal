package co.com.icesi.backend.controller;

import co.com.icesi.backend.api.RoleAPI;
import co.com.icesi.backend.dto.request.RoleDTO;
import co.com.icesi.backend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.com.icesi.backend.api.RoleAPI.BASE_ROLE_URL;

@RestController
@RequestMapping(BASE_ROLE_URL)
@AllArgsConstructor
public class RoleController implements RoleAPI {
    private final RoleService roleService;

    @Override
    public RoleDTO getRole(String roleName) {
        return roleService.getRole(roleName);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        return roleService.saveRole(roleDTO);
    }
}
