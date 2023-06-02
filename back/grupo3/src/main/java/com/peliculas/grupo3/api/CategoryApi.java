package com.peliculas.grupo3.api;

import org.springframework.web.bind.annotation.RequestMapping;

import static com.peliculas.grupo3.api.CategoryApi.BASE_CATEGORY_URL;

@RequestMapping(value = BASE_CATEGORY_URL)
public interface CategoryApi {
    String BASE_CATEGORY_URL = "/categories";

    //TODO crear los metodos para el crud de categorias
}
