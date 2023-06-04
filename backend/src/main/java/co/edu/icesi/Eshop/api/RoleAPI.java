package co.edu.icesi.Eshop.api;

import co.edu.icesi.Eshop.dto.RoleDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface RoleAPI {

    String BASE_ROLE_URL = "/roles";

    @GetMapping("/{name}")
    RoleDTO getRoleByName(@PathVariable String name);

    @PostMapping("/create")
    RoleDTO createRole(@Valid @RequestBody RoleDTO roleDTO);
}
