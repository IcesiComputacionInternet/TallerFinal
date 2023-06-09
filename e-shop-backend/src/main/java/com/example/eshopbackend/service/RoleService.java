package com.example.eshopbackend.service;

import com.example.eshopbackend.dto.RoleDTO;
import com.example.eshopbackend.mapper.RoleMapper;
import com.example.eshopbackend.model.Role;
import com.example.eshopbackend.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

@Builder
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleDTO save(RoleDTO roleToSave){
        //Validate if roleName already exists. Throw error otherwise
        Optional<Role> existingRole = roleRepository.findByRoleName(roleToSave.getRoleName());
        if (existingRole.isPresent()) {
            throw new RuntimeException("Error: The role name '" + roleToSave.getRoleName() + "' is already taken.");
        }
        //Map from DTO to Role
        Role role = roleMapper.fromRoleDTO(roleToSave);
        //Generate ID
        role.setRoleId(UUID.randomUUID());

        return roleMapper.fromRole(roleRepository.save(role));
    }

    public List<RoleDTO> getAll(){
        return roleRepository.findAll().stream().map(roleMapper::fromRole).toList();
    }




}
