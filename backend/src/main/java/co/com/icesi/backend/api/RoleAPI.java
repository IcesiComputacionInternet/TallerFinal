package co.com.icesi.backend.api;

import co.com.icesi.backend.dto.request.RoleDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(RoleAPI.BASE_ROLE_URL)
public interface RoleAPI {
    String BASE_ROLE_URL="/roles";
    @GetMapping("/{name}")
    RoleDTO getRole(@PathVariable("name") String roleName);
    List<RoleDTO> getAllRoles();
    @PostMapping("/create")
    RoleDTO createRole(@RequestBody @Valid RoleDTO roleDTO);
}
