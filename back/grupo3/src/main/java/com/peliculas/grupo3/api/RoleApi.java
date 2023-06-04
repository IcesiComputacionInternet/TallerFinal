package com.peliculas.grupo3.api;

import com.peliculas.grupo3.dto.RoleDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = RoleApi.BASE_ROLE_URL)
public interface RoleApi {
    String BASE_ROLE_URL = "/roles";

    //TODO crear los metodos para el crud de roles

    @PostMapping("/")
    RoleDTO createRole(@RequestBody RoleDTO roleDTO);

    @GetMapping("/all")
    List<RoleDTO> getAllRoles();
}
