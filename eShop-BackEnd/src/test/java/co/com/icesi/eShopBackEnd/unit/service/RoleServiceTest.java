package co.com.icesi.eShopBackEnd.unit.service;
import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import co.com.icesi.eShopBackEnd.dto.response.RoleDTO;
import co.com.icesi.eShopBackEnd.mapper.RoleMapper;
import co.com.icesi.eShopBackEnd.mapper.RoleMapperImpl;
import co.com.icesi.eShopBackEnd.model.Role;
import co.com.icesi.eShopBackEnd.repository.RoleRepository;
import co.com.icesi.eShopBackEnd.service.RoleService;
import co.com.icesi.eShopBackEnd.unit.matcher.RoleMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
public class RoleServiceTest {
    private RoleService roleService;

    private RoleRepository roleRepository;

    @BeforeEach
    public void init(){
        roleRepository = mock(RoleRepository.class);
        RoleMapper roleMapper = spy(RoleMapperImpl.class);
        roleService = new RoleService(roleRepository, roleMapper);
    }
    @Test
    public void testCreateRole(){
        roleService.save(roleDTO());
        Role Role1 = Role.builder()
                .roleName("Teacher")
                .description("Is a teacher the university Icesi")
                .build();
        verify(roleRepository, times(1)).save(argThat(new RoleMatcher(Role1)));
    }
    public CreateRoleDTO roleDTO(){
        return CreateRoleDTO.builder()
                .roleName("Teacher")
                .description("Is a teacher the university Icesi")
                .build();
    }
    @Test
    public void testCreateRoleWithExistingName(){
        when(roleRepository.roleExists(any())).thenReturn(true);
        try{
            roleService.save(roleDTO());
        }catch (RuntimeException exception){
            assertEquals("Existing data", exception.getMessage());
        }
    }
}