package com.icesi.backend.service.impl;

import com.icesi.backend.DTO.RoleCreateDTO;
import com.icesi.backend.mappers.RoleMapper;
import com.icesi.backend.models.Role;
import com.icesi.backend.respositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    public Optional<Role> createRole(RoleCreateDTO roleCreateDTO){
        Optional<String> role = Optional.of(roleCreateDTO.getRoleName());

        if(role.isPresent()){
            Optional<Role> existingRole = roleRepository.findByName(role.get());

            if(existingRole.isPresent()){
                throw new DuplicateKeyException("Role already exists");
            }

            Role newRole = roleMapper.fromRoleCreateDTO(roleCreateDTO);
            return Optional.of(roleRepository.save(newRole));
        }

        return Optional.empty();
    }


}
