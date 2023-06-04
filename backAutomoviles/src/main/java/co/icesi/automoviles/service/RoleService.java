package co.icesi.automoviles.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.icesi.automoviles.dto.RoleCreateDTO;
import co.icesi.automoviles.dto.RoleShowDTO;
import co.icesi.automoviles.error.exception.DetailBuilder;
import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.util.ShopExceptionBuilder;
import co.icesi.automoviles.mapper.RoleMapper;
import co.icesi.automoviles.model.Role;
import co.icesi.automoviles.repository.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;  
    private final RoleMapper roleMapper;
    
    public RoleShowDTO registerRole(RoleCreateDTO roleCreateDTO) {
        if(!roleNameAvailable(roleCreateDTO.getRoleName())) {
            throw ShopExceptionBuilder.createShopException(
                "There is already a role with the name: " + roleCreateDTO.getRoleName(),
                HttpStatus.BAD_REQUEST,
                new DetailBuilder(ErrorCode.ERR_400, "name", "There is already a role with the name " + roleCreateDTO.getRoleName())
            ).get();
        }

        Role role = roleMapper.fromRoleCreateDTO(roleCreateDTO);
        role.setRoleId(UUID.randomUUID());
        return roleMapper.fromRoleToRoleShowDTO(roleRepository.save(role));
    }

    private boolean roleNameAvailable(String roleName) {
        if(roleRepository.findByRoleName(roleName).isPresent()) {
            return false;
        }
        return true;
    }

}
