package co.edu.icesi.automoviles.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.automoviles.dto.RoleCreateDTO;
import co.edu.icesi.automoviles.dto.RoleShowDTO;

@RequestMapping("/roles")  
public interface RoleAPI {

    public static final String ROOT_PATH = "/roles";

    @PostMapping
    public RoleShowDTO registerRole(@Valid @RequestBody RoleCreateDTO roleCreateDTO);
}
