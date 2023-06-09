package co.com.icesi.eShop_Back.unit.service;


import co.com.icesi.eShop_Back.mapper.RoleMapper;
import co.com.icesi.eShop_Back.mapper.RoleMapperImpl;
import co.com.icesi.eShop_Back.repository.RoleRepository;
import co.com.icesi.eShop_Back.service.RoleService;
import co.com.icesi.eShop_Back.dto.request.RoleDTO;
import co.com.icesi.eShop_Back.error.exception.CustomException;
import co.com.icesi.eShop_Back.model.Role;
import co.com.icesi.eShop_Back.unit.matcher.RoleMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    private RoleService roleService;
    private RoleRepository roleRepository;

    private RoleMapper roleMapper;

    @BeforeEach
    public void setup() {
        roleRepository = mock(RoleRepository.class);
        roleMapper = spy(RoleMapperImpl.class);
        roleService = new RoleService(roleRepository, roleMapper);
    }

    @Test
    public void testCreateRole() {
        roleService.create(defaultRoleDTO());
        verify(roleMapper, times(1)).fromRoleDTO(any());
        verify(roleRepository, times(1)).save(argThat(new RoleMatcher(defaultRole())));
    }

    @Test
    public void testCreateRoleWhenNameAlreadyExist(){
        when(roleRepository.existsByName(any())).thenReturn(true);
        try{
            roleService.create(defaultRoleDTO());
        }catch (CustomException e){
            assertEquals("Name already exists", e.getMessage());
        }
    }

    private RoleDTO defaultRoleDTO() {
        return RoleDTO.builder()
                .name("name")
                .description("description")
                .build();
    }

    private Role defaultRole() {
        return Role.builder()
                .name("name")
                .description("description")
                .build();
    }

}