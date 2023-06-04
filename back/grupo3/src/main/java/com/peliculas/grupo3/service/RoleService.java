package com.peliculas.grupo3.service;

import com.peliculas.grupo3.dto.RoleDTO;
import com.peliculas.grupo3.mapper.RoleMapper;
import com.peliculas.grupo3.model.Role;
import com.peliculas.grupo3.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RoleService {
    //TODO implementar metodos del repositorio

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleDTO save(RoleDTO roleDTO){
        if(roleRepository.findByName(roleDTO.getName()).isPresent()){
            throw new RuntimeException("El rol ya existe");
        }

        Role role = roleMapper.fromRoleDTO(roleDTO);
        role.setRoleId(UUID.randomUUID());
        roleRepository.save(role);
        return roleDTO;
    }

    public List<RoleDTO> findAll(){
        return roleRepository.findAll().stream().map(roleMapper::fromRole).toList();
    }


}
