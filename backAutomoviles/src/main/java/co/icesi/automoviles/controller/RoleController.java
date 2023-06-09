package co.icesi.automoviles.controller;

import co.icesi.automoviles.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.automoviles.api.RoleAPI;
import co.icesi.automoviles.dto.RoleCreateDTO;
import co.icesi.automoviles.dto.RoleShowDTO;
import co.icesi.automoviles.service.RoleService;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleController implements RoleAPI{

    private final RoleService roleService;

    @Override
    public ResponseEntity<PageResponse<RoleShowDTO>> getAllRoles(Integer page, Integer perPage, String sortBy, String sortDirection) {
        int indexPage = page -1;
        Page<RoleShowDTO> roles = roleService.getAllRoles(indexPage, perPage, sortBy, sortDirection);
        PageResponse<RoleShowDTO> response = new PageResponse<>(roles, new RoleShowDTO[0]);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public RoleShowDTO registerRole(RoleCreateDTO roleCreateDTO) {
        return roleService.registerRole(roleCreateDTO);
    }

    @Override
    public List<RoleShowDTO> getAllRolesList() {
        return roleService.getAllRolesList();
    }

}
