package co.com.icesi.eShopBackEnd.service;

import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import co.com.icesi.eShopBackEnd.error.enums.ErrorCode;
import co.com.icesi.eShopBackEnd.error.util.ArgumentsExceptionBuilder;
import co.com.icesi.eShopBackEnd.error.util.DetailBuilder;
import co.com.icesi.eShopBackEnd.mapper.RoleMapper;
import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public CreateRoleDTO save(CreateRoleDTO role) {

        boolean exists = roleRepository.roleExists(role.name());
        if (exists){
            throw ArgumentsExceptionBuilder.createArgumentsException(
                    "Existing data",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_406,"Role name")
            );
            //throw new ArgumentsException("Role name already exist");

        }
        Role newRole = roleMapper.fromCreateRoleDTO(role);
        newRole.setRoleId(UUID.randomUUID());
        return roleMapper.fromRole(roleRepository.save(newRole));
    }
}
