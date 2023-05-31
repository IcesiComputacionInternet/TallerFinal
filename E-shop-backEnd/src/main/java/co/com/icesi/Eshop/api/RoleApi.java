package co.com.icesi.Eshop.api;

import co.com.icesi.Eshop.dto.request.RoleDTO;
import co.com.icesi.Eshop.dto.response.RoleResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(RoleApi.BASE_URL)
public interface RoleApi {

    String BASE_URL = "/api/roles";

    @PostMapping("/create")
    RoleResponseDTO createRole(RoleDTO roleResponseDTO);

    @PostMapping("/update")
    RoleResponseDTO updateRole(RoleDTO roleResponseDTO);

    @PostMapping("/delete")
    RoleResponseDTO deleteRole(RoleDTO roleResponseDTO);

    @PostMapping("/all")
    List<RoleResponseDTO> getAllRoles();
}
