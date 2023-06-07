package co.icesi.automoviles.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.CategoryShowDTO;

@RequestMapping("/categories")
public interface CategoryAPI {
    
    public static final String ROOT_PATH = "/categories";

    @PostMapping
    public CategoryShowDTO registerCategory(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO);

}
