package co.com.icesi.Eshop.service;

import co.com.icesi.Eshop.dto.request.RoleDTO;
import co.com.icesi.Eshop.dto.response.RoleResponseDTO;
import co.com.icesi.Eshop.mapper.RoleMapper;
import co.com.icesi.Eshop.model.Role;
import co.com.icesi.Eshop.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleResponseDTO createRole(RoleDTO roleDTO) {
        validateRoleNameDontExist(roleDTO.getRoleName());
        Role role = roleMapper.toRole(roleDTO);
        role.setRoleId(UUID.randomUUID());
        return roleMapper.toRoleResponseDTO(roleRepository.save(role));
    }

    private void validateRoleNameDontExist(String roleName) {
        roleRepository.findByRoleName(roleName).ifPresent(role -> {
            throw new RuntimeException("Role with " + roleName + " already exists");
        });
    }

    public RoleResponseDTO updateRole(RoleDTO roleDTO) {
        Role roleInDB = roleRepository.findByRoleName(roleDTO.getRoleName()).orElseThrow(() -> new RuntimeException("Role with " + roleDTO.getRoleName() + " does not exists"));
        roleInDB.setDescription(roleDTO.getDescription());
        return roleMapper.toRoleResponseDTO(roleRepository.save(roleInDB));
    }

    public RoleResponseDTO deleteRole(String roleName) {
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Role with " + roleName + " does not exists"));
        roleRepository.delete(role);
        return roleMapper.toRoleResponseDTO(role);
    }

    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponseDTO).toList();
    }
}
