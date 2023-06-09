package com.peliculas.grupo3.unit;

import com.peliculas.grupo3.dto.RoleDTO;
import com.peliculas.grupo3.mapper.RoleMapper;
import com.peliculas.grupo3.mapper.RoleMapperImpl;

import com.peliculas.grupo3.model.Role;
import com.peliculas.grupo3.repository.RoleRepository;
import com.peliculas.grupo3.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class RoleServiceTest {

    private RoleMapper roleMapper;

    private RoleService roleService;

    private RoleRepository roleRepository;


    @BeforeEach
    public void init(){
        roleRepository = mock(RoleRepository.class);
        roleMapper = spy(RoleMapperImpl.class);
        roleService = new RoleService(roleRepository,roleMapper);
    }

    private final Role defaultRole = Role.builder()
            .roleId(UUID.randomUUID())
            .description("Rol de prueba")
            .name("nombre de rol de prueba")
            .build();


    @Test
    public void testCreateRole(){
        roleService.save(defaultRoleDTO());
        Role role = defaultRole;
        verify(roleRepository,times(1)).findByName(argThat(name->name.equals(role.getName())));
    }



    private RoleDTO defaultRoleDTO(){
        return RoleDTO.builder()
                .description("Rol de prueba")
                .name("nombre de rol de prueba")
                .build();
    }

}
