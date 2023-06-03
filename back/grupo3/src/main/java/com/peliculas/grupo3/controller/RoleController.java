package com.peliculas.grupo3.controller;

import com.peliculas.grupo3.api.RoleApi;
import com.peliculas.grupo3.dto.RoleDTO;
import com.peliculas.grupo3.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RoleController implements RoleApi {

    private final RoleService roleService;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }
    //TODO implementar metodos de la api


}
