package com.peliculas.grupo3.api;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = RoleApi.BASE_ROLE_URL)
public interface RoleApi {
    String BASE_ROLE_URL = "/roles";

    //TODO crear los metodos para el crud de roles
}
