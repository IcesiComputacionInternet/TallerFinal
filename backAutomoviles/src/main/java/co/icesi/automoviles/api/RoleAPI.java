package co.icesi.automoviles.api;

import javax.validation.Valid;

import co.icesi.automoviles.dto.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.icesi.automoviles.dto.RoleCreateDTO;
import co.icesi.automoviles.dto.RoleShowDTO;

import java.util.List;


@RequestMapping("/roles")  
public interface RoleAPI {

    public static final String ROOT_PATH = "/roles";

    @GetMapping
    public ResponseEntity<PageResponse<RoleShowDTO>> getAllRoles(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "per_page", defaultValue = "10") Integer perPage,
            @RequestParam(name = "sort", defaultValue = "roleName") String sortBy,
            @RequestParam(name = "sort_dir", defaultValue = "asc") String sortDirection);

    @PostMapping
    public RoleShowDTO registerRole(@Valid @RequestBody RoleCreateDTO roleCreateDTO);


    @GetMapping("list")
    List<RoleShowDTO> getAllRolesList();
}
