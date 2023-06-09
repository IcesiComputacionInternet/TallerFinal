package com.example.eshopbackend.controller;

import com.example.eshopbackend.api.RoleApi;
import com.example.eshopbackend.dto.RoleDTO;
import com.example.eshopbackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleController implements RoleApi {

    private final RoleService roleService;
    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @Override
    public List<RoleDTO> getAll() {
        return roleService.getAll();
    }
}
