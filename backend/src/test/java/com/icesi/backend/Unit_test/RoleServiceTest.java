package com.icesi.backend.Unit_test;

import com.icesi.backend.DTO.RoleCreateDTO;
import com.icesi.backend.mappers.RoleMapper;
import com.icesi.backend.models.Role;
import com.icesi.backend.respositories.RoleRepository;
import com.icesi.backend.service.impl.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMapper roleMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRole_Success() {
        // Arrange
        RoleCreateDTO roleCreateDTO = new RoleCreateDTO();
        roleCreateDTO.setRoleName("ROLE_USER");

        Role role = new Role();
        role.setName("ROLE_USER");

        when(roleCreateDTO.getRoleName()).thenReturn(role.getName());
        when(roleRepository.findByName(role.getName())).thenReturn(Optional.empty());
        when(roleMapper.fromRoleCreateDTO(roleCreateDTO)).thenReturn(role);
        when(roleRepository.save(role)).thenReturn(role);

        // Act
        Optional<Role> result = roleService.createRole(roleCreateDTO);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(role, result.get());
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    public void testCreateRole_RoleAlreadyExists() {
        // Arrange
        RoleCreateDTO roleCreateDTO = new RoleCreateDTO();
        roleCreateDTO.setRoleName("ROLE_USER");

        Role existingRole = new Role();
        existingRole.setName("ROLE_USER");

        when(roleCreateDTO.getRoleName()).thenReturn(existingRole.getName());
        when(roleRepository.findByName(existingRole.getName())).thenReturn(Optional.of(existingRole));

        // Act and Assert
        Assertions.assertThrows(DuplicateKeyException.class, () -> roleService.createRole(roleCreateDTO));
        verify(roleRepository, never()).save(any(Role.class));
    }

    @Test
    public void testCreateRole_NoRoleName() {
        // Arrange
        RoleCreateDTO roleCreateDTO = new RoleCreateDTO();

        when(roleCreateDTO.getRoleName()).thenReturn(null);

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> roleService.createRole(roleCreateDTO));
        verify(roleRepository, never()).save(any(Role.class));
    }

    // Add more test cases for other scenarios if needed
}
