package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.RoleDTO;
import co.edu.icesi.Eshop.error.exception.DetailBuilder;
import co.edu.icesi.Eshop.error.exception.ErrorCode;
import co.edu.icesi.Eshop.mapper.RoleMapper;
import co.edu.icesi.Eshop.model.Role;
import co.edu.icesi.Eshop.model.Roles;
import co.edu.icesi.Eshop.repository.RoleRepository;
import co.edu.icesi.Eshop.security.EShopSecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static co.edu.icesi.Eshop.error.util.EShopExceptionBuilder.createEShopException;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleDTO save(RoleDTO roleDTO){

        verifyRoleName(roleDTO.getRoleName());
        String roleName = getRoleName(roleDTO.getRoleName());
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
        String name = roleName.toUpperCase();
        if (roleRepository.findByName(name).isPresent()){
            throw createEShopException(
                    "Duplicated role name",
                    HttpStatus.CONFLICT,
                    new DetailBuilder(ErrorCode.ERR_DUPLICATED, "Role with name", roleName)
            ).get();
        }
    }

    public List<RoleDTO> getAllRoles(){
        adminAuthorizationOnly();
        return roleRepository.findAll().stream().map(roleMapper::fromRole).toList();
    }

    private void adminAuthorizationOnly(){
        String currentUserRole = EShopSecurityContext.getCurrentUserRole();

        if (!currentUserRole.equalsIgnoreCase(String.valueOf(Roles.ADMIN))){
            throw createEShopException(
                    "Unauthorized",
                    HttpStatus.UNAUTHORIZED,
                    new DetailBuilder(ErrorCode.ERR_LOGIN,"You are not authorized")
            ).get();
        }
    }

    public RoleDTO getRoleByName(String roleName){
        return roleMapper.fromRole(roleRepository.findByName(roleName).orElseThrow(createEShopException("Role not found",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "Role with name", roleName)
        )));
    }
}
