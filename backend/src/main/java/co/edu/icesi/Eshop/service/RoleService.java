package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.RoleDTO;
import co.edu.icesi.Eshop.mapper.RoleMapper;
import co.edu.icesi.Eshop.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleDTO getRoleByName(String roleName){
        return roleMapper.fromRole(roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found")));
    }
}
