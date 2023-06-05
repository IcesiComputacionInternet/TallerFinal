package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.RoleDTO;
import co.edu.icesi.Eshop.mapper.RoleMapper;
import co.edu.icesi.Eshop.model.Role;
import co.edu.icesi.Eshop.model.Roles;
import co.edu.icesi.Eshop.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleDTO save(RoleDTO roleDTO){

        verifyRoleName(roleDTO.getName());
        String roleName = getRoleName(roleDTO.getName());
        Role role = roleMapper.fromRoleDTO(roleDTO);

        role.setRoleId(UUID.randomUUID());
        role.setRoleName(roleName);

        return roleMapper.fromRole(roleRepository.save(role));
    }

    private String getRoleName(String roleName){

        roleName = putAdminRole(roleName);
        roleName = putUserRole(roleName);
        roleName = putShopRole(roleName);

        return roleName;
    }

    private String putAdminRole(String roleName){

        if (roleName.equalsIgnoreCase(String.valueOf(Roles.ADMIN))){
            roleName = String.valueOf(Roles.ADMIN);
        }

        return roleName;
    }

    private String putUserRole(String roleName){

        if (roleName.equalsIgnoreCase(String.valueOf(Roles.USER))){
            roleName = String.valueOf(Roles.USER);
        }

        return roleName;
    }

    private String putShopRole(String roleName){

        if (roleName.equalsIgnoreCase(String.valueOf(Roles.SHOP))){
            roleName = String.valueOf(Roles.SHOP);
        }

        return roleName;
    }

    private void verifyRoleName(String roleName){
        if (roleRepository.findByName(roleName).isPresent()){
            //Toca hacer lo del error duplicated
        }
    }

    public List<RoleDTO> getAllRoles(){
        adminAuthorizationOnly();
        return roleRepository.findAll().stream().map(roleMapper::fromRole).toList();
    }

    private void adminAuthorizationOnly(){
        //Validar que el rol es un admin
        if (true){

        }
    }

    public RoleDTO getRoleByName(String roleName){
        return roleMapper.fromRole(roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found")));
    }
}
