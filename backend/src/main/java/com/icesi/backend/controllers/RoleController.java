package com.icesi.backend.controllers;

import com.icesi.backend.DTO.RoleCreateDTO;
import com.icesi.backend.models.Role;
import com.icesi.backend.service.impl.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public Role createRole(RoleCreateDTO roleCreateDTO){
        return roleService.createRole(roleCreateDTO).get();
    }
}
