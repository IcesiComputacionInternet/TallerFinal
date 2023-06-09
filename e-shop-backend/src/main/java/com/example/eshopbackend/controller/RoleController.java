package com.example.eshopbackend.controller;

import com.example.eshopbackend.api.RoleApi;
import com.example.eshopbackend.dto.RoleDTO;
import com.example.eshopbackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RoleController implements RoleApi {

    private final RoleService roleService;
    @Override
    public void createRole(RoleDTO roleDTO) {
        roleService.save(roleDTO);
    }
}
