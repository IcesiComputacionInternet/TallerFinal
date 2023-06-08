package co.com.icesi.eShop_Back.service;

import co.com.icesi.eShop_Back.dto.request.RoleDTO;
import co.com.icesi.eShop_Back.mapper.RoleMapper;
import co.com.icesi.eShop_Back.model.Role;
import co.com.icesi.eShop_Back.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @SneakyThrows
    public RoleDTO create(RoleDTO roleDTO){

        boolean nameExists = roleRepository.existsByName(roleDTO.name());

        if (nameExists){ throw new RuntimeException("Name already exists");}

        Role role = roleMapper.fromRoleDTO(roleDTO);
        role.setRoleId(UUID.randomUUID());
        return roleMapper.fromRole(roleRepository.save(role));

    }
}
