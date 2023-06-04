package co.com.icesi.backend.service;

import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.RoleDTO;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.RoleMapper;
import co.com.icesi.backend.model.Role;
import co.com.icesi.backend.repository.RoleRepository;
import co.com.icesi.backend.security.CellphoneSecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final CellphoneShopExceptionBuilder exceptionBuilder;

    public RoleDTO saveRole(RoleDTO roleDTO){
        checkPermissions();
        roleRepository.findByName(roleDTO.getName()).orElseThrow(
                () -> exceptionBuilder.duplicatedValueException(
                        "Another role already has this name.", roleDTO.getName()));

        Role newRole = roleMapper.fromRoleDTO(roleDTO);
        newRole.setRoleId(UUID.randomUUID());
        roleRepository.save(newRole);
        return roleMapper.fromRoleToRoleDTO(newRole);
    }

    public void checkPermissions() {
        if(!CellphoneSecurityContext.getCurrentUserRole().equals(UserRole.ADMIN)){
            throw exceptionBuilder.noPermissionException(
                    "Only an ADMIN user can create new roles."
            );
        }
    }
}
